package edu.dhs.bookmanagementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dhs.bookmanagementsystem.entity.Role;
import edu.dhs.bookmanagementsystem.mapper.RoleMapper;
import edu.dhs.bookmanagementsystem.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-03-25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Override
    public Map<String, Object> getRolesByConditions(String roleName, Long pageNo, Long pageSize) {
        Page<Role> rolesByPagination = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(roleName), Role::getRoleName, roleName);
        wrapper.orderByDesc(Role::getRoleId);
        rolesByPagination = this.page(rolesByPagination, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("total", rolesByPagination.getTotal());
        data.put("rows", rolesByPagination.getRecords());
        return data;
    }

    @Override
    public void addRole(Role role) {
        this.save(role);
    }

    @Override
    public void updateRole(Role role) {
        this.updateById(role);
    }
}
