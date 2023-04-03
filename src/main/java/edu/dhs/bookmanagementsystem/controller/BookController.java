package edu.dhs.bookmanagementsystem.controller;

import edu.dhs.bookmanagementsystem.common.vo.Result;
import edu.dhs.bookmanagementsystem.entity.Book;
import edu.dhs.bookmanagementsystem.service.IBookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-04-01
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    IBookService bookService;

    @GetMapping("/list")
    public Result<?> getBooksByConditions(@RequestParam(value = "bookName", required = false) String bookName, @RequestParam(value = "author", required = false) String author, @RequestParam(value = "publisher", required = false) String publisher, @RequestParam("pageNo") Long pageNo, @RequestParam("pageSize") Long pageSize) {
        Map<String, Object> data = bookService.getUsersByConditions(bookName, author, publisher, pageNo, pageSize);
        return Result.success(data);
    }

    @PostMapping
    public Result<?> addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return Result.success("new book created successfully");
    }

    @PutMapping
    public Result<?> updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        return Result.success("update book info successfully");
    }

    @GetMapping("/{id}")
    public Result<Book> getBookById(@PathVariable("id") Integer id) {
        Book book = bookService.getBookById(id);
        return Result.success(book);
    }

    @DeleteMapping("/{id}")
    public Result<Book> deleteBookById(@PathVariable("id") Integer id) {
        bookService.deleteBookById(id);
        return Result.success("delete book successfully");
    }
}
