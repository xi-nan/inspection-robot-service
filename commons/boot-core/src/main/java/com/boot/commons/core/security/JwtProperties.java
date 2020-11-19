package com.boot.commons.core.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * JwtProperties
 *
 * @author XINAN
 * @date 2019/7/22
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String tokenHeader = "Authorization";

    private String secret = "boot_";

    private Duration expiration = Duration.ofDays(7);

}