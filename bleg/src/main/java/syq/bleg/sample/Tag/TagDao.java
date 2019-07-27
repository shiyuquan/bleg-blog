package syq.bleg.sample.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import syq.bleg.intermediatetable.article2tag.Article2TagDao;
import syq.bleg.base.db.jdbc.MyJdbcTemplate;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author shiyuquan
 * @Date 2018/10/18 10:39
 * @Description TODO
 */
@Repository
public class TagDao {

    @Autowired
    private Article2TagDao article2TagDao;

    @Resource
    private MyJdbcTemplate myJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer batchInsertTag(List<Tag> tags) {
        String sql = "INSERT into tag(id,tagname) VALUES(?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, tags.get(i).getId());
                preparedStatement.setString(2, tags.get(i).getTagName());
            }

            @Override
            public int getBatchSize() {
                return tags.size();
            }
        });
        return 0;
    }
}
