package org.urusso.vgcteamwars.config.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "cache")
public class CacheConfiguration {
    private long jwtTtl;
    private long refreshJwtTtl;
}
