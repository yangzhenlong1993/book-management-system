package edu.dhs.bookmanagementsystem.thirdparty.oss;

import com.aliyun.oss.OSSClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @program: book-management-system
 * @description: oss testing
 * @author: Zhenlong Yang
 * @create: 2023-03-31 20:59
 **/
@SpringBootTest
@Slf4j
public class AliyunOSSTest {

    @Resource
    OSSClient ossClient;

    @Test
    public void testUpload() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("C:\\Users\\yangz\\Pictures\\Screenshots\\Screenshot 2023-03-03 012653.png");
        ossClient.putObject("dhs-book-management", "circuit.png", inputStream);
        ossClient.shutdown();
        log.debug("file uploaded");
    }
}
