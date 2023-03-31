package edu.dhs.bookmanagementsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * borrower + staff+ book
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-04-01
 */
@TableName("borrowing_archives")
@ApiModel(value = "BorrowingArchives对象", description = "borrower + staff+ book")
@Data
public class BorrowingArchives implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer borrowerId;

    private Integer staffId;

    private Integer bookId;

    private LocalDateTime borrowDate;

    private LocalDateTime returnDate;

    private Integer extended;

    private String comment;

    private Integer deleted;
}
