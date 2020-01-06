package com.woowacourse.tecobrary.user.infra.util;

import com.woowacourse.tecobrary.user.ui.dto.UserJwtInfoDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    private static final long JWT_TOKEN_VALIDITY = 60 * 60 * 24 * 7; // one week

    private static String SECRET;

    @Value("${jwt.secret}")
    private void injectSecret(final String secret) {
        SECRET = secret;
    }

    public static String generateToken(final UserJwtInfoDto userJwtInfoDto) {
        return doGenerateToken(jwtClaims(userJwtInfoDto), jwtHeaders());
    }

    private static Map<String, Object> jwtClaims(final UserJwtInfoDto userJwtInfoDto) {
        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("id", userJwtInfoDto.getId());
        claims.put("email", userJwtInfoDto.getEmail());
        claims.put("name", userJwtInfoDto.getName());
        claims.put("authorization", userJwtInfoDto.getAuthorization());
        claims.put("avatarUrl", userJwtInfoDto.getAvatarUrl());
        return claims;
    }

    private static Map<String, Object> jwtHeaders() {
        Map<String, Object> headers = new LinkedHashMap<>();
        headers.put("alg", "HS256");
        headers.put("typ", "JWT");
        return headers;
    }

    public static Boolean validateToken(final String token, final UserJwtInfoDto userJwtInfoDto) {
        final String userId = getUserId(token);
        return (userId.equals(userJwtInfoDto.getId()) && !isTokenExpired(token));
    }

    public static Boolean isTokenExpired(final String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public static String getUserId(final String token) {
        return (String) getClaimFromToken(token, claims -> claims.get("id"));
    }

    public static String getUserAuthorization(final String token) {
        return (String) getClaimFromToken(token, claims -> claims.get("authorization"));
    }

    private static String doGenerateToken(final Map<String, Object> claims, final Map<String, Object> headers) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .setHeader(headers)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .compact();
    }

    private static Date getExpirationDateFromToken(final String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private static <T> T getClaimFromToken(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private static Claims getAllClaimsFromToken(final String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
