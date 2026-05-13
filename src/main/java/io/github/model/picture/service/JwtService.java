package io.github.model.picture.service;

import io.github.model.picture.auth.JwtAuthPayload;
import io.github.model.picture.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * JWT 签发与校验服务
 */
@Service
public class JwtService {

    private final SecretKey secretKey;

    private final long expirationSeconds;

    public JwtService(JwtProperties jwtProperties) {
        String secret = jwtProperties.getSecret();
        if (secret == null || secret.isBlank()) {
            throw new IllegalStateException("mp.security.jwt.secret 未配置");
        }
        this.secretKey = buildSecretKey(secret);
        this.expirationSeconds = jwtProperties.getExpirationSeconds();
    }

    /**
     * 将配置密钥转换为 HMAC-SHA 可用的 SecretKey
     */
    private static SecretKey buildSecretKey(String secret) {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        if (secretBytes.length >= 32) {
            return Keys.hmacShaKeyFor(secretBytes);
        }
        try {
            return Keys.hmacShaKeyFor(MessageDigest.getInstance("SHA-256").digest(secretBytes));
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("SHA-256 不可用", exception);
        }
    }

    /**
     * 为登录用户签发访问令牌
     */
    public String createToken(String userId, String username, String nickName, Integer role) {
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + expirationSeconds * 1000L);
        return Jwts.builder()
                .subject(userId)
                .claim("username", username)
                .claim("nickName", nickName)
                .claim("role", role)
                .issuedAt(now)
                .expiration(expiresAt)
                .signWith(secretKey)
                .compact();
    }

    /**
     * 校验并解析访问令牌
     */
    public JwtAuthPayload parseAndValidate(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        Integer role = claims.get("role", Integer.class);
        return new JwtAuthPayload(
                claims.getSubject(),
                claims.get("username", String.class),
                claims.get("nickName", String.class),
                role
        );
    }

    public long getExpirationSeconds() {
        return expirationSeconds;
    }
}
