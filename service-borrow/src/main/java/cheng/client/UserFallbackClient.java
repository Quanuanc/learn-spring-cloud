package cheng.client;

import cheng.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserFallbackClient implements UserClient {
    @Override
    public User findUserById(int uid) {
        User user = new User();
        user.setName("fallback用户");
        user.setSex("Male");
        user.setUid(0);
        return user;
    }
}
