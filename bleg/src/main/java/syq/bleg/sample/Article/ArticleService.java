package syq.bleg.sample.Article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author shiyuquan
 * @Date 2018/10/17 20:03
 * @Description TODO
 */
public interface ArticleService {
    Article addArticle(Article article);

    Page<Article> getArticleByPage(Pageable pageable);

    Article getArticle(String id);
}
