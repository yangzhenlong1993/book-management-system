package edu.dhs.bookmanagementsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-03-25
 */
@ApiModel(value = "Menu对象", description = "")
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    private String component;

    private String path;

    private String redirect;

    private String name;

    private String title;

    private String icon;

    private Integer parentId;

    private String isLeaf;

    private Boolean hidden;

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", component=" + component +
                ", path=" + path +
                ", redirect=" + redirect +
                ", name=" + name +
                ", title=" + title +
                ", icon=" + icon +
                ", parentId=" + parentId +
                ", isLeaf=" + isLeaf +
                ", hidden=" + hidden +
                "}";
    }
}
