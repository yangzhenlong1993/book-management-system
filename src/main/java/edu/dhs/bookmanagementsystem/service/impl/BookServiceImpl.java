package edu.dhs.bookmanagementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dhs.bookmanagementsystem.common.util.JwtUtil;
import edu.dhs.bookmanagementsystem.common.util.UserThreadLocal;
import edu.dhs.bookmanagementsystem.entity.Book;
import edu.dhs.bookmanagementsystem.mapper.BookMapper;
import edu.dhs.bookmanagementsystem.service.IBookService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-04-01
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {
    @Resource
    JwtUtil jwtUtil;

    @Override
    public Map<String, Object> getUsersByConditions(String bookName, String author, String publisher, Long pageNo, Long pageSize) {
        Page<Book> booksByPagination = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(bookName), Book::getBookName, bookName);
        wrapper.eq(StringUtils.hasLength(author), Book::getAuthor, author);
        wrapper.eq(StringUtils.hasLength(publisher), Book::getPublisher, publisher);
        wrapper.orderByDesc(Book::getId);
        booksByPagination = this.page(booksByPagination, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("total", booksByPagination.getTotal());
        data.put("rows", booksByPagination.getRecords());
        return data;
    }

    @Override
    public void addBook(Book book) {
        //insert new book info into the book table
        book.setStaffId(UserThreadLocal.getUser().getId());
        book.setCreateTime(LocalDateTime.now());
        this.baseMapper.insert(book);

    }

    @Override
    public void updateBook(Book book) {
        this.baseMapper.updateById(book);
    }

    @Override
    public Book getBookById(Integer id) {
        Book book = this.baseMapper.selectById(id);
        return book;
    }

    @Override
    public void deleteBookById(Integer id) {
        this.baseMapper.deleteById(id);
    }
}
