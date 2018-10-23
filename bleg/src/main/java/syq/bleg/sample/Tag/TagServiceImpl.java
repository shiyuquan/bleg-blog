package syq.bleg.sample.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shiyuquan
 * @Date 2018/10/18 10:39
 * @Description TODO
 */
@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagDao tagDao;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public void batchSaveTags(List<Tag> listTags) {
        tagDao.batchInsertTag(listTags);
    }

    @Override
    public Tag findByName(String name) {
        return tagRepository.findByTagName(name);
    }

    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }
}
