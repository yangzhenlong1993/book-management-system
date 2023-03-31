package edu.dhs.bookmanagementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dhs.bookmanagementsystem.entity.Role;
import edu.dhs.bookmanagementsystem.entity.RoleMenu;
import edu.dhs.bookmanagementsystem.mapper.RoleMapper;
import edu.dhs.bookmanagementsystem.mapper.RoleMenuMapper;
import edu.dhs.bookmanagementsystem.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
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
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private RoleMenuMapper roleMenuMapper;

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
        //save role list
        this.baseMapper.insert(role);
        //save role-to-menu list
        saveRoleMenuList(role);
    }

    private void saveRoleMenuList(Role role) {
        if (role.getMenuIdList() != null) {
            for (Integer menuId : role.getMenuIdList()) {
                roleMenuMapper.insert(new RoleMenu(null, role.getRoleId(), menuId));
            }
        }
    }

    @Override
    public void updateRole(Role role) {
        //update role table
        this.baseMapper.updateById(role);
        //delete role_menu by role_id
        deleteRoleMenuList(role.getRoleId());
        //insert new data in role_menu
        saveRoleMenuList(role);
    }

    private void deleteRoleMenuList(Integer roleId) {
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleMenu::getRoleId, roleId);
        roleMenuMapper.delete(wrapper);
    }

    @Override
    public Role getRoleById(Integer id) {
        Role role = this.baseMapper.selectById(id);
        List<Integer> menuIdList = roleMenuMapper.getMenuIdListByRoleId(id);
        role.setMenuIdList(menuIdList);
        return role;
    }

    @Override
    public void deleteRoleById(Integer id) {
        //delete data in role table
        this.baseMapper.deleteById(id);
        //delete data in role_menu table
        deleteRoleMenuList(id);
    }
}
