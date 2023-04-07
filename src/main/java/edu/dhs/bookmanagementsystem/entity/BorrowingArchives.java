package edu.dhs.bookmanagementsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.dhs.bookmanagementsystem.common.util.LocalDateDeserializer;
import edu.dhs.bookmanagementsystem.common.util.LocalDateSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

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

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate borrowDate;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate returnDate;

    private Integer extended;

    private String comment;

    private Integer deleted;
}
