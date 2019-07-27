package syq.bleg.base.db.sql;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import syq.bleg.base.exception.MyException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shiyuquan
 * Create Time: 2019/6/27 10:20
 */
@Component
public class JdbcTemplatePlus extends JdbcTemplate {

    private String databaseType;

    public JdbcTemplatePlus(DataSource dataSource) {
        super(dataSource);
        setDatabaseType();
    }

    /**
     * 分页查询
     * @param objCode 对象名
     * @param searchCondition 组装条件
     * @return DataTable
     */
    public DataTable<Map<String, Object>> queryForPage(String objCode, SearchCondition searchCondition) {
        DataTable<Map<String, Object>> dataTable = new DataTable<>();
        StringBuilder selectCount = new StringBuilder("SELECT count(*） FROM \"basedata\".").append(objCode);
        StringBuilder sql = new StringBuilder("SELECT * FROM \"basedata\".").append(objCode);
        List<Object> sqlParam = new ArrayList<>();
        // 拼接where的子句，获取条件参数
        String where = DbUtils.loadSearchParam(sqlParam, searchCondition.getParams());

        // 查询总数
        Integer totalCount = queryForObject(selectCount.append(where).toString(), sqlParam.toArray(), Integer.class);

        // 排序
        String orderBy = DbUtils.loadSort(searchCondition.getSorts());
        sql.append(where).append(orderBy);

        // 分页
        DbUtils.parseLimit(sql, searchCondition.getPageNum(), searchCondition.getPageSize(), databaseType);

        // 查询
        List<Map<String, Object>> data = queryForList(sql.toString(), sqlParam.toArray());

        // 填充DataTable
        dataTable.setPageNum(searchCondition.getPageNum() + 1);
        dataTable.setPageSize(searchCondition.getPageSize());
        dataTable.setRows(data);
        dataTable.setTotal(totalCount);

        return dataTable;
    }

    /**
     * 根据params 查询数组
     * @param objCode 对象名
     * @param params 条件
     * @return list
     */
    public List<Map<String, Object>> queryForList(String objCode, String params) {
        StringBuilder sql = new StringBuilder("SELECT * FROM \"basedata\".").append(objCode);
        List<Object> sqlPatam = new ArrayList<>();
        String where = DbUtils.loadSearchParam(sqlPatam, params);
        sql.append(where);
        return queryForList(sql.toString(), sqlPatam.toArray());
    }

    /**
     * 查询单条数据
     * @param objCode 对象名
     * @param params 查询条件
     * @return map
     */
    public Map<String, Object> queryOne(String objCode, String params) {
        List<Map<String, Object>> dataList = queryForList(objCode, params);
        Map<String, Object> data= new HashMap<>(16);
        if (dataList.size() > 1) {
            throw new MyException("here has more than one data!");
        }
        if (dataList.size() == 1) {
            data = dataList.get(0);
        }
        return data;
    }

    /**
     * 设置数据库类型
     */
    public void setDatabaseType() {
        if (!StringUtils.isEmpty(databaseType)) {
            return;
        }
        DataSource dataSource = getDataSource();
        try(Connection connection = dataSource.getConnection()) {
            String jdbcUrl = connection.getMetaData().getURL();
            String dbType = DbUtils.getDbtype(jdbcUrl);
            setDatabaseType(dbType);
        } catch (Exception e) {
            throw new MyException("Error: setDatabaseType Fail ! Cause:" + e);
        }
    }


    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }
}
