package com.sparta.devstar_be.user.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.devstar_be.user.dto.LoginRequestDto;
import com.sparta.devstar_be.user.dto.LoginSuccessResponseDto;
import com.sparta.devstar_be.user.security.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("[log] login: 로그인 시도");
        try {
            // json(email, password) -> object(requestDto)
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);

            // user
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword()
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException{
        log.info("[log] login: 로그인 성공 -> JWT 생성");
        response.setStatus(HttpServletResponse.SC_OK);

        String email = ((UserDetailsImpl) authentication.getPrincipal()).getUser().getEmail();
        String token = jwtUtil.createToken(email);
        jwtUtil.addJwtToCookie(token, response);

        Long userId = ((UserDetailsImpl) authentication.getPrincipal()).getUser().getUserId();
        String valueToken = jwtUtil.substringToken(token);
        LoginSuccessResponseDto loginSuccessResponseDto = new LoginSuccessResponseDto(userId, valueToken);
        response.getWriter().write(new ObjectMapper().writeValueAsString(loginSuccessResponseDto));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("[log] login: 로그인 실패");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}