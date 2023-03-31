package edu.dhs.bookmanagementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dhs.bookmanagementsystem.entity.BorrowingArchives;
import edu.dhs.bookmanagementsystem.mapper.BorrowingArchivesMapper;
import edu.dhs.bookmanagementsystem.service.IBorrowingArchivesService;
import org.springframework.stereotype.Service;

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

}
