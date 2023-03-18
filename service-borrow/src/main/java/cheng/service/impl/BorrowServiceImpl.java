package cheng.service.impl;

import cheng.client.BookClient;
import cheng.client.UserClient;
import cheng.entity.Book;
import cheng.entity.Borrow;
import cheng.entity.User;
import cheng.entity.UserBorrowDetail;
import cheng.mapper.BorrowMapper;
import cheng.service.BorrowService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Resource
    BorrowMapper mapper;

    @Resource
    UserClient userClient;

    @Resource
    BookClient bookClient;

    @Override
    public UserBorrowDetail getUserBorrowDetailByUid(int uid) {
        List<Borrow> borrow = mapper.getBorrowsByUid(uid);
        User user = userClient.getUserById(uid);
        List<Book> bookList = borrow.stream()
                .map(b -> bookClient.getBookById(b.getBid()))
                .collect(Collectors.toList());
        return new UserBorrowDetail(user, bookList);
    }

    @Override
    @GlobalTransactional
    public boolean doBorrow(int uid, int bid) {
        if (bookClient.bookRemain(bid) < 1) {
            throw new RuntimeException("图书数量不足");
        }
        if (userClient.userRemain(uid) < 1) {
            throw new RuntimeException("用户借阅量不足");
        }

        if (!bookClient.bookBorrow(bid)) {
            throw new RuntimeException("在借阅图书时出现了错误");
        }

        if (mapper.getBorrow(uid, bid) != null) {
            throw new RuntimeException("此书籍已经被此用户借阅了！");
        }

        if (mapper.addBorrow(uid, bid) <= 0) {
            throw new RuntimeException("在录入借阅信息时出现错误！");
        }

        if (!userClient.userBorrow(uid)) {
            throw new RuntimeException("在借阅时出现错误！");
        }

        return true;
    }

}
