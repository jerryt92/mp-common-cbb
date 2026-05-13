package io.github.model.picture.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * JWT 配置项
 */
@ConfigurationProperties(prefix = "mp.security.jwt")
public class JwtProperties {

    private String secret;

    private long expirationSeconds = 604800L;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpirationSeconds() {
        return expirationSeconds;
    }

    public void setExpirationSeconds(long expirationSeconds) {
        this.expirationSeconds = expirationSeconds;
    }
}
