package com.example.MyBookShopApp.data.repo;

import com.example.MyBookShopApp.data.book.links.Book2UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Books2UserRepository extends JpaRepository<Book2UserEntity, Integer> {
    List<Book2UserEntity> findBook2UserEntitiesByBookId(Integer book);
}
