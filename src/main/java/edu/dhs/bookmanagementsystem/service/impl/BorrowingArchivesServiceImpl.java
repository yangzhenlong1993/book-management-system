package edu.dhs.bookmanagementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dhs.bookmanagementsystem.common.vo.BorrowingHistoryVo;
import edu.dhs.bookmanagementsystem.entity.Book;
import edu.dhs.bookmanagementsystem.entity.BorrowingArchives;
import edu.dhs.bookmanagementsystem.entity.User;
import edu.dhs.bookmanagementsystem.mapper.BookMapper;
import edu.dhs.bookmanagementsystem.mapper.BorrowingArchivesMapper;
import edu.dhs.bookmanagementsystem.mapper.UserMapper;
import edu.dhs.bookmanagementsystem.service.IBorrowingArchivesService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

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

    @Resource
    ThreadPoolExecutor executor;
    @Resource
    BookMapper bookMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public List<BorrowingHistoryVo> getByBookId(Integer id) throws ExecutionException, InterruptedException {
        //1. search borrowing_archive table to get basic info
        CompletableFuture<List<BorrowingHistoryVo>> BorrowingHistoryVoFuture = CompletableFuture.supplyAsync(() -> {
            LambdaQueryWrapper<BorrowingArchives> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(BorrowingArchives::getBookId, id);
            List<BorrowingArchives> borrowingArchives = this.baseMapper.selectList(wrapper);
            List<BorrowingHistoryVo> voList = borrowingArchives.stream().map(borrowingArchive -> {
                BorrowingHistoryVo vo = new BorrowingHistoryVo();
                BeanUtils.copyProperties(borrowingArchive, vo);
                return vo;
            }).collect(Collectors.toList());
            return voList;
        }, executor);
        //2. search user table for staff info, this step can be done with step 3 concurrently
        CompletableFuture<Void> staffFuture = BorrowingHistoryVoFuture.thenAcceptAsync((res) -> {
            res.stream().map(vo -> {
                LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(User::getId, vo.getStaffId());
                User user = userMapper.selectOne(wrapper);
                vo.setStaff(user);
                return vo;
            }).collect(Collectors.toList());
        }, executor);
        //3. search user table for borrower info
        CompletableFuture<Void> borrowerFuture = BorrowingHistoryVoFuture.thenAcceptAsync((res) -> {
            res.stream().map(vo -> {
                LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(User::getId, vo.getBorrowerId());
                User user = userMapper.selectOne(wrapper);
                vo.setBorrower(user);
                return vo;
            }).collect(Collectors.toList());
        }, executor);
        CompletableFuture<Void> bookFuture = BorrowingHistoryVoFuture.thenAcceptAsync((res) -> {
            res.stream().map(vo -> {
                LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(Book::getId, vo.getBookId());
                Book book = bookMapper.selectOne(wrapper);
                vo.setBook(book);
                return vo;
            }).collect(Collectors.toList());
        }, executor);
        CompletableFuture.allOf(BorrowingHistoryVoFuture, staffFuture, borrowerFuture, bookFuture).get();
        List<BorrowingHistoryVo> voList = BorrowingHistoryVoFuture.get();

        return voList;
    }
}