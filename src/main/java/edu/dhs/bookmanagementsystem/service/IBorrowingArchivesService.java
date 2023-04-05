package edu.dhs.bookmanagementsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.dhs.bookmanagementsystem.entity.BorrowingArchives;

import java.util.List;

/**
 * <p>
 * borrower + staff+ book 服务类
 * </p>
 *
 * @author Zhenlong Yang
 * @since 2023-04-01
 */
public interface IBorrowingArchivesService extends IService<BorrowingArchives> {

    List<BorrowingArchives> getByBookId(Integer id);
}
