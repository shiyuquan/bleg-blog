package syq.bleg.intermediatetable.article2tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import syq.bleg.utils.jdbc.MyJdbcTemplate;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * @Author shiyuquan
 * @Date 2018/10/18 14:06
 * @Description TODO
 */
@Repository
public class Article2TagDao {

    @Resource
    private MyJdbcTemplate myJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addArticle2tag(List<String> tagIds, String articleId) {
        String sql = "INSERT into article_tag(id, articleid, tagid) VALUES(?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, UUID.randomUUID().toString());
                preparedStatement.setString(2, articleId);
                preparedStatement.setString(3, tagIds.get(i));
            }
            @Override
            public int getBatchSize() {
                return tagIds.size();
            }
        });
    }
}
