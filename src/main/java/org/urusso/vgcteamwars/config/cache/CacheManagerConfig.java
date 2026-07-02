package org.urusso.vgcteamwars.config.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.urusso.vgcteamwars.common.constants.CacheName;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
public class CacheManagerConfig {
    private final CacheConfiguration cacheConfig;

    @Bean
    public CacheManager cacheManager() {
        CaffeineCache jwtCache = new CaffeineCache(CacheName.JWT_CACHE,
                Caffeine.newBuilder()
                        .expireAfterWrite(cacheConfig.getJwtTtl(), TimeUnit.MINUTES)
                        .maximumSize(2)
                        .build()
        );

        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(List.of(jwtCache));

        return manager;
    }
}
