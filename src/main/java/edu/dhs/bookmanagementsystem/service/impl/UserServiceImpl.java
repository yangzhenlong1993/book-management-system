package edu.dhs.bookmanagementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dhs.bookmanagementsystem.common.util.JwtUtil;
import edu.dhs.bookmanagementsystem.entity.User;
import edu.dhs.bookmanagementsystem.mapper.UserMapper;
import edu.dhs.bookmanagementsystem.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtUtil jwtUtil;

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
        User loginUser = null;
        try {
            loginUser = jwtUtil.parseToken(token, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> data = new HashMap<>();
        data.put("name", loginUser.getUsername());
        data.put("avatar", loginUser.getAvatar());

        //according to the user id, find the user's roles
        List<String> roleList = this.baseMapper.getRoleNameByUserId(loginUser.getId());
        data.put("roles", roleList);
        return data;

    }

    @Override
    public void logout(String token) {
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.save(user);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(null);
        this.updateById(user);
    }
}
