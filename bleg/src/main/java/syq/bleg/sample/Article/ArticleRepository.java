package syq.bleg.sample.Article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

/**
 * @Author shiyuquan
 * @Date 2018/10/17 20:04
 * @Description TODO
 */
public interface ArticleRepository extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {
}
