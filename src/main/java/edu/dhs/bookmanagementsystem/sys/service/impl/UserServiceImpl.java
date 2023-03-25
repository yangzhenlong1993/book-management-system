package edu.dhs.bookmanagementsystem.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dhs.bookmanagementsystem.sys.entity.User;
import edu.dhs.bookmanagementsystem.sys.mapper.UserMapper;
import edu.dhs.bookmanagementsystem.sys.service.IUserService;
import org.springframework.stereotype.Service;

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

}
