package edu.dhs.bookmanagementsystem.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: book-management-system
 * @description: common result vo
 * @author: Zhenlong Yang
 * @create: 2023-03-25 15:54
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(20000, "success", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(20000, "success", data);
    }

    public static <T> Result<T> success(String message) {
        return new Result<>(20000, message, null);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(20000, message, data);
    }

    public static <T> Result<T> fail() {
        return new Result<>(30000, "fail", null);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<>(30000, "fail", data);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(30000, message, null);
    }

    public static <T> Result<T> fail(String message, T data) {
        return new Result<>(30000, message, data);
    }
}
