package edu.dhs.bookmanagementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dhs.bookmanagementsystem.common.util.JwtUtil;
import edu.dhs.bookmanagementsystem.common.util.UserThreadLocal;
import edu.dhs.bookmanagementsystem.entity.Menu;
import edu.dhs.bookmanagementsystem.entity.User;
import edu.dhs.bookmanagementsystem.entity.UserRole;
import edu.dhs.bookmanagementsystem.mapper.UserMapper;
import edu.dhs.bookmanagementsystem.mapper.UserRoleMapper;
import edu.dhs.bookmanagementsystem.service.IMenuService;
import edu.dhs.bookmanagementsystem.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private IMenuService menuService;

    @Override
    public Map<String, Object> login(User user) {
        //search database based on username
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        User loginUser = this.baseMapper.selectOne(wrapper);
        //if loginUser is not null and the input password match with the password in the database，create a token,store user info into the redis
        if (loginUser != null && passwordEncoder.matches(user.getPassword(), loginUser.getPassword())) {
            //JWT
            loginUser.setPassword(null);
            //create jwt
            String token = jwtUtil.createToken(loginUser);
            //return result
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        //according to the token, get the user info from JWT
        User loginUser = UserThreadLocal.getUser();

        Map<String, Object> data = new HashMap<>();
        data.put("name", loginUser.getUsername());
        data.put("avatar", loginUser.getAvatar());

        //according to the user id, find the user's roles
        List<String> roleList = this.baseMapper.getRoleNameByUserId(loginUser.getId());
        data.put("roles", roleList);

        //permission list
        List<Menu> menuList = menuService.getMenuListByUserId(loginUser.getId());
        data.put("menuList", menuList);
        return data;

    }

    @Override
    public void logout(String token) {
        //remove ThreadLocal data when log out in case that OOM
    }

    @Override
    public Map<String, Object> getUsersByConditions(String username, String phone, Long pageNo, Long pageSize) {
        Page<User> usersByPagination = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(username), User::getUsername, username);
        wrapper.eq(StringUtils.hasLength(phone), User::getPhone, phone);
        wrapper.orderByDesc(User::getId);
        usersByPagination = this.page(usersByPagination, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("total", usersByPagination.getTotal());
        data.put("rows", usersByPagination.getRecords());
        return data;
    }

    @Override
    public void addUser(User user) {
        //insert data in user table
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.baseMapper.insert(user);
        //insert data in user_role table
        List<Integer> roleIdList = user.getRoleIdList();
        saveUserRoleList(user.getId(), roleIdList);
    }

    private void saveUserRoleList(Integer id, List<Integer> roleIdList) {
        if (roleIdList != null) {
            for (Integer roleId : roleIdList) {
                userRoleMapper.insert(new UserRole(null, id, roleId));
            }
        }
    }

    @Override
    public void updateUser(User user) {
        //update user table
        user.setPassword(null);
        this.baseMapper.updateById(user);
        //clear the original user_role list
        deleteUserRoleList(user.getId());
        //set new user_role list
        saveUserRoleList(user.getId(), user.getRoleIdList());
    }

    private void deleteUserRoleList(Integer id) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, id);
        userRoleMapper.delete(wrapper);
    }

    @Override
    public User getUserById(Integer id) {
        User user = this.baseMapper.selectById(id);
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, id);
        List<UserRole> userRoles = userRoleMapper.selectList(wrapper);
        List<Integer> roleIds = userRoles.stream().map(userRole -> userRole.getRoleId()).collect(Collectors.toList());
        user.setRoleIdList(roleIds);
        return user;
    }

    @Override
    public void deleteUserById(Integer id) {
        this.baseMapper.deleteById(id);
        deleteUserRoleList(id);
    }
}
