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
@ApiModel(value = "User Object", description = "")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private Integer status;

    private String avatar;

    private Integer deleted;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", email=" + email +
                ", phone=" + phone +
                ", status=" + status +
                ", avatar=" + avatar +
                ", deleted=" + deleted +
                "}";
    }
}
