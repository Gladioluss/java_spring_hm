package com.example.MyBookShopApp.data.service;

import com.example.MyBookShopApp.data.entity.GenreEntity;
import com.example.MyBookShopApp.data.repo.BooksByGenresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BooksByGenresService {
    BooksByGenresRepository repository;

    @Autowired
    public BooksByGenresService(BooksByGenresRepository genreRepository) {
        this.repository = genreRepository;
    }

    public Map<GenreEntity, Map<GenreEntity, List<GenreEntity>>> getGenreMap() {

        Map<GenreEntity, Map<GenreEntity, List<GenreEntity>>> genreTreeList = new HashMap<>();

        List<GenreEntity> genreList = repository.findAll(Sort.by(Sort.Direction.DESC, "amount"));
        for (GenreEntity genreOne : genreList) {
            Map<GenreEntity, List<GenreEntity>> genreTwoList = new HashMap<>();
            for (GenreEntity genreTwo : genreList) {
                List<GenreEntity> genreThreeList = new ArrayList<>();
                for (GenreEntity genreThree : genreList) {
                    if (genreThree.getParentId() == genreTwo.getId()) {
                        genreThreeList.add(genreThree);
                    }
                }
                if (genreTwo.getParentId() == genreOne.getId()) {
                    genreTwoList.put(genreTwo, genreThreeList);
                }
            }
            if (genreOne.getParentId() == 0) {
                genreTreeList.put(genreOne, genreTwoList);
            }
        }
        return genreTreeList;
    }

    public GenreEntity getPageBySlug(String slug) {
        return repository.findGenreEntityBySlug(slug);
    }

    public GenreEntity getPageById(Integer id) {
        return repository.findGenreEntityById(id);
    }

    public void addAmount() {
        List<GenreEntity> bookList = repository.findAll();
        bookList.forEach(genre -> {
            genre.setAmount(genre.getBookList().size());
            repository.save(genre);
        });
    }
}
