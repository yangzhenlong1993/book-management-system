package edu.dhs.bookmanagementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dhs.bookmanagementsystem.entity.BorrowingArchives;
import edu.dhs.bookmanagementsystem.mapper.BorrowingArchivesMapper;
import edu.dhs.bookmanagementsystem.service.IBorrowingArchivesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * borrower + staff+ book 服务实现类
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-04-01
 */
@Service
public class BorrowingArchivesServiceImpl extends ServiceImpl<BorrowingArchivesMapper, BorrowingArchives> implements IBorrowingArchivesService {

    @Override
    public List<BorrowingArchives> getByBookId(Integer id) {
        LambdaQueryWrapper<BorrowingArchives> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BorrowingArchives::getBookId, id);
        List<BorrowingArchives> borrowingArchives = this.baseMapper.selectList(wrapper);
        return borrowingArchives;
    }
}
