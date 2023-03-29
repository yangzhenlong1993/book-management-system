package edu.dhs.bookmanagementsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.dhs.bookmanagementsystem.entity.Role;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-03-25
 */
public interface IRoleService extends IService<Role> {

    Map<String, Object> getRolesByConditions(String roleName, Long pageNo, Long pageSize);

    void addRole(Role role);

    void updateRole(Role role);
}
