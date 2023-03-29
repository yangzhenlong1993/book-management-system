package edu.dhs.bookmanagementsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.dhs.bookmanagementsystem.entity.User;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-03-25
 */
public interface UserMapper extends BaseMapper<User> {
    List<String> getRoleNameByUserId(Integer id);
}
