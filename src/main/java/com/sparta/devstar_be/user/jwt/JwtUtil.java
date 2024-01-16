package com.sparta.devstar_be.user.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Base64;
import java.util.Date;


// 1. JWT 생성 데이터
@Slf4j
@Component
public class JwtUtil {
    // Cookie name 값
    public static final String AUTHORIZATION_HEADER = "Authorization";
    // 사용자 권한 값 key
//    public static final String AUTHORIZATION_KEY = "auth";
    // token 식별값
    public static final String BEARER_PREFIX = "Bearer ";
    // token 만료 시간(24시간)
    private final long TOKEN_TIME = 24 * 60 * 60 * 1000L;
    // Base64 encode 적용 secret key
    @Value("${jwt.secret.key}")
    private String secretKey;
    // secret key를 담을 객체
    private Key key;
    // 암호화 알고리즘 설정
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    // 로그 설정
    public static final Logger logger = LoggerFactory.getLogger("JWT Log");

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // 2-1. [JwtAuthenticationFilter] JWT 생성
    public String createToken(String email) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(email) // 사용자 식별값
                        .signWith(key, signatureAlgorithm) // 서명 추가
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME))
                        .compact();
    }

    // 2-2. [JwtAuthenticationFilter] / JWT Cookie 저장
    public void addJwtToCookie(String token, HttpServletResponse res) {
        try {
            token = URLEncoder.encode(token, "utf-8").replaceAll("\\+", "%20");

            Cookie cookie = new Cookie(AUTHORIZATION_HEADER, token);
            cookie.setPath("/");

            res.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
    }

    // 3-1. [JwtAuthorizationFilter] JWT substring
    public String substringToken(String tokenValue) {
        if (StringUtils.hasText(tokenValue) && tokenValue.startsWith(BEARER_PREFIX)) {
            return tokenValue.substring(7);
        }
        logger.error("Not Found Token");
        throw new NullPointerException("Not Found Token");
    }

    // 3-2. JwtAuthorizationFilter / JWT 검증
    public boolean validateToken(String token) {
        try {
            // secret key를 사용해 token 서명을 확인함
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            logger.error("Invalid JWT signature");
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims is empty");
        }
        return false;
    }

    // 3-3. JwtAuthorizationFilter / JWT에서 사용자 정보 가져오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    // 3-4. HttpServletRequest Cookie 값에서 JWT 가져오기
    public String getTokenFromRequest(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AUTHORIZATION_HEADER)) {
                    try {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        return null;
                    }
                }
            }
        }
        return null;
    }
}