package syq.bleg.sample.Tag;

import java.util.List;

/**
 * @Author shiyuquan
 * @Date 2018/10/18 10:38
 * @Description TODO
 */
public interface TagService {
    void batchSaveTags(List<Tag> listTags);

    Tag findByName(String name);

    Tag saveTag(Tag tag);

    List<Tag> getAll();
}
