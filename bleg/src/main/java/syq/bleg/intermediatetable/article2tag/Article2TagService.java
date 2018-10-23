package syq.bleg.intermediatetable.article2tag;

import java.util.List;

/**
 * @Author shiyuquan
 * @Date 2018/10/18 14:57
 * @Description TODO
 */
public interface Article2TagService {
    void addArticle2tag(List<String> tagIds, String articleId);
}
