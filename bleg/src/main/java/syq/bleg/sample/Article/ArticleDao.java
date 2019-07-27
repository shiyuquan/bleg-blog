package syq.bleg.sample.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import syq.bleg.base.db.jdbc.MyJdbcTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author shiyuquan
 * @Date 2018/10/17 20:03
 * @Description TODO
 */
@Repository
public class ArticleDao {

    @Resource
    private MyJdbcTemplate myJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Article getArticleById(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from article where 1=1 and id = :id");
        Map<String, Object> map = new HashMap<>();
        map.put(new String("id"), id);
        return myJdbcTemplate.queryForBean(sql.toString(), map, Article.class);
    }

    public Page<Article> getArticleByPage(Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM article WHERE 1=1");
        return myJdbcTemplate.queryForPage(sql.toString(), pageable, Article.class);
    }
}
