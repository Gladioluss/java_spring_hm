package com.example.MyBookShopApp.data.service;

import com.example.MyBookShopApp.data.entity.TagEntity;
import com.example.MyBookShopApp.data.repo.BooksByTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksByTagService {
    private final BooksByTagRepository repository;

    @Autowired
    public BooksByTagService(BooksByTagRepository repository) {
        this.repository = repository;
    }

    public List<TagEntity> getPageOfTagsBooks() {
        addAmount();
        return repository.findAll();
    }

    public TagEntity getPageBySlug(String slug) {
        return repository.findTagEntityBySlug(slug);
    }

    public void addAmount() {
        List<TagEntity> bookList = repository.findAll();
        bookList.forEach(tagEntity -> {
            tagEntity.setAmount(tagEntity.getBookList().size());
            repository.save(tagEntity);
        });
    }

    public List<TagEntity> getTagsByBook(Integer id) {
        return repository.getBookByTag(id);
    }
}
