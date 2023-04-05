package edu.dhs.bookmanagementsystem.controller;

import edu.dhs.bookmanagementsystem.common.vo.Result;
import edu.dhs.bookmanagementsystem.entity.BorrowingArchives;
import edu.dhs.bookmanagementsystem.service.IBorrowingArchivesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * borrower + staff+ book 前端控制器
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-04-01
 */
@RestController
@RequestMapping("/borrowingArchives")
public class BorrowingArchivesController {

    @Resource
    IBorrowingArchivesService borrowingArchivesService;

    @GetMapping("/{id}")
    public Result<?> getBorrowHistoryById(@PathVariable("id") Integer id) {
        List<BorrowingArchives> history = borrowingArchivesService.getByBookId(id);
        return null;
    }
}
