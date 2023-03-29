package edu.dhs.bookmanagementsystem.common.util.test;

import edu.dhs.bookmanagementsystem.common.util.JwtUtil;
import edu.dhs.bookmanagementsystem.entity.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @program: book-management-system
 * @description: JwtUtilTest
 * @author: Zhenlong Yang
 * @create: 2023-03-28 21:59
 **/
@SpringBootTest
public class JwtUtilTest {
    @Resource
    private JwtUtil jwtUtil;

    @Test
    public void testCreateJwt() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPhone("123456789");
        user.setEmail("654654@qq.com");
        String token = jwtUtil.createToken(user);
        System.out.println(token);
    }

    @Test
    public void parseToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJmMWNjY2VjZi1kYWNkLTRmZDgtYmQzOC00ZDhiY2FiMTM1NWMiLCJzdWIiOiJ7XCJlbWFpbFwiOlwiNjU0NjU0QHFxLmNvbVwiLFwicGhvbmVcIjpcIjEyMzQ1Njc4OVwiLFwidXNlcm5hbWVcIjpcInpoYW5nc2FuXCJ9IiwiaXNzIjoic3lzdGVtIiwiaWF0IjoxNjgwMDA2NzU2LCJleHAiOjE2ODAwMDg1NTZ9.DBxFs-1NnTWOtjp5wMjSJrXz-owmCGXsGilWtflGC3I";
        Claims claims = jwtUtil.parseToken(token);
        System.out.println(claims);
    }

    @Test
    public void parseTokenByClazz() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJmMWNjY2VjZi1kYWNkLTRmZDgtYmQzOC00ZDhiY2FiMTM1NWMiLCJzdWIiOiJ7XCJlbWFpbFwiOlwiNjU0NjU0QHFxLmNvbVwiLFwicGhvbmVcIjpcIjEyMzQ1Njc4OVwiLFwidXNlcm5hbWVcIjpcInpoYW5nc2FuXCJ9IiwiaXNzIjoic3lzdGVtIiwiaWF0IjoxNjgwMDA2NzU2LCJleHAiOjE2ODAwMDg1NTZ9.DBxFs-1NnTWOtjp5wMjSJrXz-owmCGXsGilWtflGC3I";
        User user = jwtUtil.parseToken(token, User.class);
        System.out.println(user);
    }
}
