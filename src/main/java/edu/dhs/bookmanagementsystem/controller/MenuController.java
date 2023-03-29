package edu.dhs.bookmanagementsystem.controller;

import edu.dhs.bookmanagementsystem.common.vo.Result;
import edu.dhs.bookmanagementsystem.entity.Menu;
import edu.dhs.bookmanagementsystem.service.IMenuService;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/menu")
public class MenuController {
    @Resource
    IMenuService menuService;

    @ApiOperation("get the list of menus")
    @GetMapping
    public Result<List<Menu>> getAllMenu() {
        List<Menu> menuList = menuService.getAllMenu();
        return Result.success(menuList);
    }
}
