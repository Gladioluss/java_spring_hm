package com.example.MyBookShopApp.data;

import org.springframework.stereotype.Service;

@Service
public class BlackListTokenService {
    private final BlackListTokenEntityRepository blackListTokenEntityRepository;

    public BlackListTokenService(BlackListTokenEntityRepository blackTokenRepository) {
        this.blackListTokenEntityRepository = blackTokenRepository;
    }

    public boolean isTokenInBlackList(String token) {
        return blackListTokenEntityRepository.findByBlackToken(token).isPresent();
    }

}
