package edu.dhs.bookmanagementsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.dhs.bookmanagementsystem.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-03-25
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenuListByUserId(@Param("userId") Integer userId, @Param("parentId") Integer parentId);
}
