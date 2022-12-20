package com.example.MyBookShopApp.data.repo;

import com.example.MyBookShopApp.data.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksByGenresRepository extends JpaRepository<GenreEntity, Integer> {

    GenreEntity findGenreEntityBySlug(String slug);

    GenreEntity findGenreEntityById(Integer id);
}
