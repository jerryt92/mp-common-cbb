package io.github.model.picture.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * JWT 公共配置注册
 */
@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtAuthConfiguration {
}
