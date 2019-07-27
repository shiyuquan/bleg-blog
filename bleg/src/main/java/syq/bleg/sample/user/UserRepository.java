package syq.bleg.sample.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author shiyuquan
 * @Date 2018/10/11 13:50
 * @Description TODO
 */
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    User findByUserNameAndPassword(String userName, String password);
    User findByUserName(String userName);
    List<User> findByUserNameAndIdNotIn(String userName, String id);
}
