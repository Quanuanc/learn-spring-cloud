package cheng.controller;

import cheng.entity.User;
import cheng.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RefreshScope
public class UserController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    UserService service;

    @Value("${test.config1}")
    String hello;

    //这里以RESTFul风格为例
    @RequestMapping("/user/{uid}")
    public User findUserById(@PathVariable("uid") int uid) {
        log.info("调用了user服务");
        log.info("config1 value: {}", hello);
        return service.getUserById(uid);
    }

    @RequestMapping("/user/remain/{uid}")
    public int getUserBookRemain(@PathVariable("uid") int uid) {
        return service.getUserBookRemain(uid);
    }

    @RequestMapping("/user/borrow/{uid}")
    public boolean userBorrow(@PathVariable("uid") int uid) {
        int remain = service.getUserBookRemain(uid);
        return service.setRemain(uid, remain - 1);
    }
}
