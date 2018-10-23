package syq.bleg.intermediatetable.article2tag;

import io.swagger.annotations.SwaggerDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shiyuquan
 * @Date 2018/10/18 14:57
 * @Description TODO
 */
@Service
public class Article2TagServiceImpl implements Article2TagService {

    @Autowired
    private Article2TagDao article2TagDao;

    @Override
    public void addArticle2tag(List<String> tagIds, String articleId) {
        article2TagDao.addArticle2tag(tagIds, articleId);
    }
}
