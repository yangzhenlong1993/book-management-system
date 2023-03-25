package edu.dhs.bookmanagementsystem.sys.controller;

import edu.dhs.bookmanagementsystem.sys.entity.User;
import edu.dhs.bookmanagementsystem.sys.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    public List<User> getAllUsers() {
        List<User> list = userService.list();
        return list;
    }
}
