package syq.bleg.sample.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * @Author shiyuquan
 * @Date 2018/10/18 10:39
 * @Description TODO
 */
public interface TagRepository extends JpaRepository<Tag, String>, JpaSpecificationExecutor<Tag> {
    Tag findByTagName(String name);
}
