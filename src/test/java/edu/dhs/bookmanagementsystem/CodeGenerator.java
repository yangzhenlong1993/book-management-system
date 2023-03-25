package edu.dhs.bookmanagementsystem;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @program: book-management-system
 * @description: mybatis plus code generator
 * @author: Zhenlong Yang
 * @create: 2023-03-25 09:56
 **/
public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/book_management_system?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Australia/Sydney", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("Zhenlong Yang") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\yangz\\IdeaProjects\\book-management-system\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("edu.dhs.bookmanagementsystem") // 设置父包名
                            .moduleName("sys") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "C:\\Users\\yangz\\IdeaProjects\\book-management-system\\src\\main\\resources\\mapper\\sys")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("menu,role,role_menu,user,user_role") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
