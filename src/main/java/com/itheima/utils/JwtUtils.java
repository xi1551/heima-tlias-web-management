package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "SVRIRUlNQQ==ITHEIMATOKENSIGNINGKEY";  // JWT签名密钥（必须>=256位）
    private static Long expire = 43200000L;          // 令牌过期时间（12小时 = 43200000毫秒）
    private static SecretKey key = Keys.hmacShaKeyFor(signKey.getBytes());  // 生成密钥


    /**
     * 生成JWT令牌
     * @return
     */
    public static String generateJwt(Map<String,Object> claims){


        String jwt = Jwts.builder()
                .addClaims(claims)                    // 添加用户数据（如用户ID、用户名等）
                .signWith(key)                        // 使用密钥签名
                .setExpiration(new Date(System.currentTimeMillis() + expire))  // 设置12小时后过期
                .compact();                           // 生成JWT字符串
        return jwt;
    }


    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)                   // 设置签名密钥验证令牌
                .build()                              // 构建parser
                .parseClaimsJws(jwt)                  // 解析JWT令牌
                .getBody();                           // 获取负载部分
        return claims;
    }

}
