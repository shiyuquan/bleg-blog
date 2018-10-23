package syq.bleg.sample.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author shiyuquan
 * @Date 2018/10/17 20:03
 * @Description TODO
 */

@Service
public class ArticleServiveImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleDao articleDao;

    @Override
    public Article addArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Page<Article> getArticleByPage(Pageable pageable) {
        return articleDao.getArticleByPage(pageable);
    }

    @Override
    public Article getArticle(String id) {
        return articleDao.getArticleById(id);
    }
}
