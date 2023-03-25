package edu.dhs.bookmanagementsystem;

import edu.dhs.bookmanagementsystem.sys.entity.User;
import edu.dhs.bookmanagementsystem.sys.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class BookManagementSystemApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void testMapper() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
