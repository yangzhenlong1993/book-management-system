package edu.dhs.bookmanagementsystem.sys.controller;

import edu.dhs.bookmanagementsystem.common.vo.Result;
import edu.dhs.bookmanagementsystem.sys.entity.User;
import edu.dhs.bookmanagementsystem.sys.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-03-25
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {
    @Resource
    private IUserService userService;

    @GetMapping("/allUsers")
    public Result<?> getAllUsers() {
        List<User> list = userService.list();
        return Result.success("all users query success", list);
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        Map<String, Object> data = userService.login(user);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail(30002, "username or password is wrong");
    }

    @GetMapping("/info")
    public Result<?> getUserInfo(@RequestParam("token") String token) {

        Map<String, Object> data = userService.getUserInfo(token);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail(30003, "login token expired, please login again");
    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token) {
        userService.logout(token);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<?> listUsersByConditions(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "phone", required = false) String phone, @RequestParam("pageNo") Long pageNo, @RequestParam("pageSize") Long pageSize) {
        Map<String, Object> data = userService.getUsersByConditions(username, phone, pageNo, pageSize);

        return Result.success(data);
    }

    @PostMapping("/user")
    public Result<?> addUser(@RequestBody User user) {
        userService.addUser(user);
        return Result.success("new user create successfully");
    }

    @PutMapping("/user")
    public Result<?> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success("update user info successfully");
    }

    @GetMapping("/user/{id}")
    public Result<User> getUserById(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    @DeleteMapping("/user/{id}")
    public Result<User> deleteUserById(@PathVariable("id") Integer id) {
        userService.removeById(id);
        return Result.success("delete user successfully");
    }
}
