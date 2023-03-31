package edu.dhs.bookmanagementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dhs.bookmanagementsystem.entity.BookStatus;
import edu.dhs.bookmanagementsystem.mapper.BookStatusMapper;
import edu.dhs.bookmanagementsystem.service.IBookStatusService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * staff+book 服务实现类
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-04-01
 */
@Service
public class BookStatusServiceImpl extends ServiceImpl<BookStatusMapper, BookStatus> implements IBookStatusService {

}
