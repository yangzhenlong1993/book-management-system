package edu.dhs.bookmanagementsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.dhs.bookmanagementsystem.common.util.LocalDateDeserializer;
import edu.dhs.bookmanagementsystem.common.util.LocalDateSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-04-01
 */
@ApiModel(value = "Book对象", description = "")
@Data
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String bookName;

    private String author;

    private String publisher;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;

    private String coverImage;

    private String description;

    private String comment;

    private LocalDateTime createTime;

    private Integer inStock;

    private Integer staffId;

    private Integer deleted;
}
