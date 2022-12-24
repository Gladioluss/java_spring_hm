package com.example.MyBookShopApp.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlackListTokenEntityRepository
        extends JpaRepository<BlackListTokenEntity, Integer> {
    Optional<BlackListTokenEntity> findByBlackToken(String blackToken);
}
