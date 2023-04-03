package edu.dhs.bookmanagementsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.dhs.bookmanagementsystem.entity.Book;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-04-01
 */
public interface IBookService extends IService<Book> {

    Map<String, Object> getUsersByConditions(String bookName, String author, String publisher, Long pageNo, Long pageSize);

    void addBook(Book book);

    void updateBook(Book book);

    Book getBookById(Integer id);

    void deleteBookById(Integer id);
}
