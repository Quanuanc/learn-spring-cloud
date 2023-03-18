package cheng.service;

import cheng.entity.Book;

public interface BookService {
    Book getBookById(int bid);

    int getRemain(int bid);

    boolean setRemain(int bid, int count);
}
