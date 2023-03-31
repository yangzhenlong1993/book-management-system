package edu.dhs.bookmanagementsystem.controller;

import edu.dhs.bookmanagementsystem.common.vo.Result;
import edu.dhs.bookmanagementsystem.entity.Role;
import edu.dhs.bookmanagementsystem.service.IRoleService;
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
@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService roleService;

    @GetMapping("/list")
    public Result<?> listRolesByConditions(@RequestParam(value = "roleName", required = false) String roleName, @RequestParam("pageNo") Long pageNo, @RequestParam("pageSize") Long pageSize) {
        Map<String, Object> data = roleService.getRolesByConditions(roleName, pageNo, pageSize);
        return Result.success(data);
    }

    @PostMapping
    public Result<?> addRole(@RequestBody Role role) {
        roleService.addRole(role);
        return Result.success("new role create successfully");
    }

    @PutMapping
    public Result<?> updateRole(@RequestBody Role role) {
        roleService.updateRole(role);
        return Result.success("update role info successfully");
    }

    @GetMapping("/{id}")
    public Result<Role> getRoleById(@PathVariable("id") Integer id) {
        Role role = roleService.getRoleById(id);
        return Result.success(role);
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteRoleById(@PathVariable("id") Integer id) {
        roleService.deleteRoleById(id);
        return Result.success("delete role successfully");
    }

    @GetMapping("/all")
    public Result<List<Role>> getAllRole() {
        List<Role> roleList = roleService.list();
        return Result.success(roleList);
    }
}
