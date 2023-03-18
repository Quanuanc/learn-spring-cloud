package cheng.service;

import cheng.entity.UserBorrowDetail;

public interface BorrowService {

    UserBorrowDetail getUserBorrowDetailByUid(int uid);

    boolean doBorrow(int uid, int bid);
}
