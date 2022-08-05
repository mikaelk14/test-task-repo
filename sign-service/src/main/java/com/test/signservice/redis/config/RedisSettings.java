package com.test.signservice.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Component
@Validated
@ConfigurationProperties(prefix = "redis")
public class RedisSettings {
    /**
     * Host for redis.
     */
    @NotNull
    String host;
    /**
     * Password for redis.
     */
    String password;
    /**
     * Port for redis.
     */
    @NotNull
    Integer port;
}
