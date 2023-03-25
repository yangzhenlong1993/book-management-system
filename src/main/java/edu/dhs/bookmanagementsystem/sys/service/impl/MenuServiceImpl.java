package edu.dhs.bookmanagementsystem.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dhs.bookmanagementsystem.sys.entity.Menu;
import edu.dhs.bookmanagementsystem.sys.mapper.MenuMapper;
import edu.dhs.bookmanagementsystem.sys.service.IMenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
