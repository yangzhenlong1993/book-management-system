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
 * staff+book
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-04-01
 */
@TableName("book_status")
@ApiModel(value = "BookStatus对象", description = "staff+book")
@Data
public class BookStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer staffId;

    private Integer bookId;

    private LocalDateTime createTime;

    private Integer inStock;
}
