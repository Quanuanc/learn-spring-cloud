package cheng.controller;

import cheng.entity.Book;
import cheng.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class BookController {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    BookService service;

    @RequestMapping("/book/{bid}")
    Book findBookById(@PathVariable("bid") int bid, HttpServletRequest request) {
        log.info("book request header: {}", request.getHeader("Test"));
        return service.getBookById(bid);
    }
}
