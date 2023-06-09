package edu.dhs.bookmanagementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dhs.bookmanagementsystem.entity.Menu;
import edu.dhs.bookmanagementsystem.mapper.MenuMapper;
import edu.dhs.bookmanagementsystem.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Menu> getAllMenu() {
        //level 1 menu
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getParentId, 0);
        List<Menu> levelOneMenuList = this.list(wrapper);
        //find sub menus
        setMenuChildren(levelOneMenuList);
        return levelOneMenuList;
    }

    @Override
    public List<Menu> getMenuListByUserId(Integer uid) {
        //level 1 menu
        List<Menu> menuList = this.baseMapper.getMenuListByUserId(uid, 0);
        //children menu
        setMenuChildrenByUserId(uid, menuList);
        return menuList;
    }

    private void setMenuChildrenByUserId(Integer uid, List<Menu> menuList) {
        if (menuList != null) {
            for (Menu menu : menuList) {
                List<Menu> subMenuList = this.baseMapper.getMenuListByUserId(uid, menu.getMenuId());
                menu.setChildren(subMenuList);
                setMenuChildrenByUserId(uid, subMenuList);
            }
        }
    }

    private void setMenuChildren(List<Menu> menuList) {
        if (menuList != null && menuList.size() > 0) {
            for (Menu menu : menuList) {
                LambdaQueryWrapper<Menu> subWrapper = new LambdaQueryWrapper<>();
                subWrapper.eq(Menu::getParentId, menu.getMenuId());
                List<Menu> subMenuList = this.list(subWrapper);
                menu.setChildren(subMenuList);
                setMenuChildren(subMenuList);
            }
        }
    }
}
