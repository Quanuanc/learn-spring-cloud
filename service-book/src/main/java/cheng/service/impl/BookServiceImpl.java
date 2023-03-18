package cheng.service.impl;

import cheng.entity.Book;
import cheng.mapper.BookMapper;
import cheng.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    BookMapper mapper;

    @Override
    public Book getBookById(int bid) {
        return mapper.getBookById(bid);
    }

    @Override
    public boolean setRemain(int bid, int count) {
        return mapper.setRemain(bid, count) > 0;
    }

    @Override
    public int getRemain(int bid) {
        return mapper.getRemain(bid);
    }
}
