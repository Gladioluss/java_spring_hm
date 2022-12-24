package com.example.MyBookShopApp.data;


import com.example.MyBookShopApp.security.jwt.JWTUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LogoutHandlerService implements LogoutHandler {
    private final JWTUtil jwtUtil;
    private final BlackListTokenEntityRepository blackListTokenRepository;

    public LogoutHandlerService(JWTUtil jwtUtil, BlackListTokenEntityRepository blackListTokenRepository) {
        this.jwtUtil = jwtUtil;
        this.blackListTokenRepository = blackListTokenRepository;
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        String token = jwtUtil.getToken(httpServletRequest);
        BlackListTokenEntity blackTokenEntity = new BlackListTokenEntity();
        blackTokenEntity.setBlackToken(token);
        blackListTokenRepository.save(blackTokenEntity);
    }
}
