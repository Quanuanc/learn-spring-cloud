package cheng.controller;

import cheng.entity.UserBorrowDetail;
import cheng.service.BorrowService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BorrowController {

    @Resource
    BorrowService service;

    @RequestMapping("/borrow/{uid}")
    UserBorrowDetail findUserBorrows(@PathVariable("uid") int uid) {
        return service.getUserBorrowDetailByUid(uid);
    }

    @GetMapping("/blocked")
    JSONObject blocked() {
        JSONObject result = new JSONObject();
        result.put("code", HttpStatus.FORBIDDEN);
        result.put("success", false);
        result.put("message", "您的请求频率过快，请稍后重试");
        return result;
    }

    @RequestMapping("/borrow/take/{uid}/{bid}")
    JSONObject borrow(@PathVariable("uid") int uid, @PathVariable("bid") int bid) {
        service.doBorrow(uid, bid);

        JSONObject object = new JSONObject();
        object.put("code", "200");
        object.put("success", false);
        object.put("message", "借阅成功！");
        return object;
    }
}
