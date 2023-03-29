package edu.dhs.bookmanagementsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.dhs.bookmanagementsystem.entity.Menu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-03-25
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getAllMenu();
}
