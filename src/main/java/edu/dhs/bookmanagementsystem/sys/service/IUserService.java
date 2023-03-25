package edu.dhs.bookmanagementsystem.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.dhs.bookmanagementsystem.sys.entity.User;

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
}