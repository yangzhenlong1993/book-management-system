package edu.dhs.bookmanagementsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.dhs.bookmanagementsystem.entity.User;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-03-25
 */
public interface IUserService extends IService<User> {

    Map<String, Object> login(User user);

    Map<String, Object> getUserInfo(String token);

    void logout(String token);

    Map<String, Object> getUsersByConditions(String username, String phone, Long pageNo, Long pageSize);

    void addUser(User user);

    void updateUser(User user);
}
