package edu.dhs.bookmanagementsystem.interceptor;

import com.alibaba.fastjson.JSON;
import edu.dhs.bookmanagementsystem.common.util.JwtUtil;
import edu.dhs.bookmanagementsystem.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @program: book-management-system
 * @description: the interceptor to validate the token from the front end
 * @author: Zhenlong Yang
 * @create: 2023-03-28 22:55
 **/
@Component
@Slf4j
public class JwtValidateInterceptor implements HandlerInterceptor {
    @Resource
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            log.debug("OPTIONS request，pass");
            return true;
        }
        String token = request.getHeader("x-token");
        log.debug(request.getRequestURI() + " need to be validated: " + token);
        if (token != null) {
            try {
                jwtUtil.parseToken(token);
                log.debug(request.getRequestURI() + "validation passed");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.debug(request.getRequestURI() + " validation failed, request is blocked");
        response.setContentType("application/json;charset=utf-8");
        Result<Map<String, Object>> fail = Result.fail(30003, "jwt not valid, please login again");
        response.getWriter().write(JSON.toJSONString(fail));
        return false; // intercept the request
    }
}
