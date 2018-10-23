package syq.bleg.base.user;

import java.util.List;

/**
 * @Author shiyuquan
 * @Date 2018/10/11 13:49
 * @Description TODO
 */
public interface UserService {
    User findUser(String userName, String password);

    User findUser(String userName);

    User saveUser(User user);

    List<User> findByUserNameAndIdNotIn(String name, String id);
}
