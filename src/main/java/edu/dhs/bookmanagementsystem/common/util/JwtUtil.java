package edu.dhs.bookmanagementsystem.common.util;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @program: book-management-system
 * @description: Jwt Utils
 * @author: Zhenlong Yang
 * @create: 2023-03-28 21:37
 **/
@Component
public class JwtUtil {
    // half hour expiration time
    private static final long JWT_EXPIRE = 30 * 60 * 1000L;
    //JWT key
    private static final String JWT_KEY = "123456";

    public String createToken(Object data) {
        //current time
        long currentTime = System.currentTimeMillis();
        //Expiration time
        long expTime = currentTime + JWT_EXPIRE;
        //build JWT
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(JSON.toJSONString(data))
                .setIssuer("system")
                .setIssuedAt(new Date(currentTime))
                .signWith(SignatureAlgorithm.HS256, encodeSecret(JWT_KEY))
                .setExpiration(new Date(expTime));
        return jwtBuilder.compact();
    }

    private SecretKey encodeSecret(String key) {
        byte[] encode = Base64.getEncoder().encode(key.getBytes());
        SecretKeySpec aes = new SecretKeySpec(encode, 0, encode.length, "AES");
        return aes;
    }

    public Claims parseToken(String token) {
        Claims body = Jwts.parser()
                .setSigningKey(encodeSecret(JWT_KEY))
                .parseClaimsJws(token)
                .getBody();
        return body;
    }

    public <T> T parseToken(String token, Class<T> clazz) {
        Claims body = Jwts.parser()
                .setSigningKey(encodeSecret(JWT_KEY))
                .parseClaimsJws(token)
                .getBody();
        return JSON.parseObject(body.getSubject(), clazz);
    }
}
