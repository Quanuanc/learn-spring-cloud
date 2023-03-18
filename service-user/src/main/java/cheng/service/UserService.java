package cheng.service;

import cheng.entity.User;

public interface UserService {
    User getUserById(int uid);

    int getUserBookRemain(int uid);

    boolean setRemain(int uid, int count);
}
