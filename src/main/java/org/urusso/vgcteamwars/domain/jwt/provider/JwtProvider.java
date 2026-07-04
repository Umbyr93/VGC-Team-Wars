package org.urusso.vgcteamwars.domain.jwt.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.urusso.vgcteamwars.common.constants.CacheName;
import org.urusso.vgcteamwars.common.constants.ErrorEnum;
import org.urusso.vgcteamwars.common.exception.BusinessException;
import org.urusso.vgcteamwars.domain.jwt.dto.RefreshTokenRequest;
import org.urusso.vgcteamwars.domain.jwt.dto.RefreshTokenResponse;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import static ch.qos.logback.core.encoder.ByteArrayUtil.hexStringToByteArray;

@Service
public class JwtProvider {
    private final String jwtSecretKey;
    private final Duration jwtExpiration;
    private final String refreshJwtSecretKey;
    private final Duration refreshJwtExpiration;
    private final CacheManager cacheManager;

    public JwtProvider(@Value("${security.jwt.secret-key}") String jwtSecretKey,
                       @Value("${security.jwt.expiration-time}") Duration jwtExpiration,
                       @Value("${security.refresh.secret-key}") String refreshJwtSecretKey,
                       @Value("${security.refresh.expiration-time}") Duration refreshJwtExpiration,
                       CacheManager cacheManager) {

        this.jwtSecretKey = jwtSecretKey;
        this.jwtExpiration = jwtExpiration;
        this.refreshJwtSecretKey = refreshJwtSecretKey;
        this.refreshJwtExpiration = refreshJwtExpiration;
        this.cacheManager = cacheManager;
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        Cache cache = cacheManager.getCache(CacheName.REFRESH_JWT_CACHE);
        if(cache == null) throw new IllegalStateException("Missing cache");

        String valueFound = cache.get(request.username(), String.class);
        if(!Strings.CI.equals(valueFound, request.refreshToken())) {
            throw new BusinessException(ErrorEnum.REFRESH_TOKEN_INVALID);
        }

        String newToken = generateToken(request.username());
        return new RefreshTokenResponse(newToken);
    }

    @Cacheable(cacheNames = CacheName.JWT_CACHE)
    public String generateToken(String username) {
        return Jwts.builder()
                .claims(new HashMap<>())
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration.toMillis()))
                .signWith(getSignInKey())
                .compact();
    }

    @Cacheable(cacheNames = CacheName.REFRESH_JWT_CACHE)
    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .claims(new HashMap<>())
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshJwtExpiration.toMillis()))
                .signWith(getRefreshSignInKey())
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            extractUsername(token); //if not valid, it breaks
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(hexStringToByteArray(jwtSecretKey));
    }
    private SecretKey getRefreshSignInKey() {
        return Keys.hmacShaKeyFor(hexStringToByteArray(refreshJwtSecretKey));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
