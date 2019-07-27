package syq.bleg.base.db.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 扩展JdbcTemplate: 支持JavaBean, 分页查询
 */
@Component
public class MyJdbcTemplate extends NamedParameterJdbcTemplate {

    private final BasicRowProcessor convert = new BasicRowProcessor();

    @Value("${dialect.database-type:postgres}")
    private String DATABASE_TYPE;

    @Autowired
    public MyJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }

    public <T> T queryForBean(String sql, final Class<T> beanType) {
        return query(sql, new ResultSetExtractor<T>() {
            @Override
            public T extractData(ResultSet rs) throws SQLException, DataAccessException {
                return rs.next() ? convert.toBean(rs, beanType) : null;
            }
        });
    }

    public <T> T queryForBean(String sql, Map<String, ?> paramMap, final Class<T> beanType) {
        return query(sql, paramMap, new ResultSetExtractor<T>() {
            @Override
            public T extractData(ResultSet rs) throws SQLException, DataAccessException {
                return rs.next() ? convert.toBean(rs, beanType) : null;
            }
        });
    }

    public <T> List<T> queryForBeanList(String sql, final Class<T> beanType) {
        return query(sql, new RowMapper<T>() {
            @Override
            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
                return convert.toBean(rs, beanType);
            }
        });
    }

    public <T> List<T> queryForBeanList(String sql, Map<String, ?> paramMap, final Class<T> beanType) {
        return query(sql, paramMap, new RowMapper<T>() {
            @Override
            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
                return convert.toBean(rs, beanType);
            }
        });
    }

    public <T> Page<T> queryForPage(String sql, Map<String, ?> paramMap, Pageable page, final Class<T> beanType)
            throws DataAccessException {
        Long totalCount = queryForCount(sql, paramMap);

        if (page != null && page.getSort() != null) {
            sql += parseSort(page);
        }

        if (page != null) {
            sql = parseLimit(sql, page);
        }

        List<T> data = queryForBeanList(sql, paramMap, beanType);

        return new PageImpl<T>(data, page, totalCount);
    }

    public <T> Page<T> queryForPage(String sql, Pageable page, final Class<T> beanType) throws DataAccessException {
        return queryForPage(sql, Collections.emptyMap(), page, beanType);
    }

    public <T> Page<T> queryForPage(String sql, Map<String, ?> paramMap, Pageable page, RowMapper<T> rowMapper)
            throws DataAccessException {
        Long totalCount = queryForCount(sql, paramMap);

        if (page.getSort() != null) {
            sql += parseSort(page);
        }

        if (page != null) {
            sql = parseLimit(sql, page);
        }

        List<T> data = super.query(sql, paramMap, rowMapper);

        return new PageImpl<T>(data, page, totalCount);
    }

    public <T> Page<T> queryForPage(String sql, Pageable page, RowMapper<T> rowMapper) throws DataAccessException {
        return queryForPage(sql, Collections.emptyMap(), page, rowMapper);
    }

    public long queryForCount(String sql) throws DataAccessException {
        String countSql = "select count(1) as _c_ from (" + sql + ") _t_";
        return super.queryForObject(countSql, Collections.emptyMap(), Long.class);
    }

    public long queryForCount(String sql, Map<String, ?> paramMap) throws DataAccessException {
        String countSql = "select count(1) as _c_ from (" + sql + ") _t_";
        return super.queryForObject(countSql, paramMap, Long.class);
    }

    private String parseLimit(String sql, Pageable page) {
        if (DatabaseType.postgres.name().equals(DATABASE_TYPE))
            return parseLimitPostgres(sql, page);
        else if (DatabaseType.mysql.name().equals(DATABASE_TYPE))
            return parseLimitMySQL(sql, page);
        else if (DatabaseType.oracle.name().equals(DATABASE_TYPE))
            return parseLimitOracle(sql, page);
        throw new java.lang.UnsupportedOperationException();
    }

    private String parseLimitPostgres(String sql, Pageable page) {
        return new StringBuilder().append(sql).append(" LIMIT ").append(page.getPageSize()).append(" OFFSET ")
                .append(page.getPageNumber() * page.getPageSize()).toString();
    }

    private String parseLimitMySQL(String sql, Pageable page) {
        return new StringBuilder().append(sql).append(" LIMIT ").append(page.getPageNumber() * page.getPageSize())
                .append(", ").append(page.getPageSize()).toString();
    }

    private String parseLimitOracle(String sql, Pageable page) {
        long end = page.getPageNumber() * page.getPageSize();
        long start = end - page.getPageSize();

        return new StringBuilder().append("SELECT * FROM (SELECT _t.*, rownum _rn FROM (").append(sql)
                .append(") _t WHERE _rn <").append(end).append(")").append(" WHERE _rn >=").append(start).toString();
    }

    private String parseSort(Pageable page) {
        Sort sort = page.getSort();
        if (sort == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" order by ");
        sort.forEach((sortItem) -> {
            sb.append(sortItem.getProperty());
            sb.append(" ");
            if (sortItem.isAscending()) {
                sb.append("ASC");
            } else {
                sb.append("DESC");
            }
            sb.append(",");
        });

        return sb.toString().substring(0, sb.length() - 1);
    }

}

