package edu.dhs.bookmanagementsystem.config;

import edu.dhs.bookmanagementsystem.interceptor.JwtValidateInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @program: book-management-system
 * @description: the configuration of interceptors
 * @author: Zhenlong Yang
 * @create: 2023-03-28 23:05
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    JwtValidateInterceptor jwtValidateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtValidateInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login",
                        "/user/logout",
                        "/error",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/**");
    }
}
