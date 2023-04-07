package edu.dhs.bookmanagementsystem.common.vo;

import edu.dhs.bookmanagementsystem.entity.Book;
import edu.dhs.bookmanagementsystem.entity.BorrowingArchives;
import edu.dhs.bookmanagementsystem.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: book-management-system
 * @description:
 * @author: Zhenlong Yang
 * @create: 2023-04-07 15:11
 **/
@Data
public class BorrowingHistoryVo extends BorrowingArchives implements Serializable {

    private Book book;
    private User borrower;
    private User staff;
}
