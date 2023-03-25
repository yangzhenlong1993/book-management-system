package edu.dhs.bookmanagementsystem.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dhs.bookmanagementsystem.sys.entity.Role;
import edu.dhs.bookmanagementsystem.sys.mapper.RoleMapper;
import edu.dhs.bookmanagementsystem.sys.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
