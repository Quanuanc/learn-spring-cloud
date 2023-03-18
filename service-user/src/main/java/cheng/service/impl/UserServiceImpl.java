package cheng.service.impl;

import cheng.entity.User;
import cheng.mapper.UserMapper;
import cheng.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper mapper;

    @Override
    public User getUserById(int uid) {
        return mapper.getUserById(uid);
    }

    @Override
    public int getUserBookRemain(int uid) {
        return mapper.getUserBookRemain(uid);
    }

    @Override
    public boolean setRemain(int uid, int count) {
        return mapper.updateBookAccount(uid, count) > 0;
    }
}
