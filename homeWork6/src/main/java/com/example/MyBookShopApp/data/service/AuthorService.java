package com.example.MyBookShopApp.data.service;

import com.example.MyBookShopApp.data.entity.Author;
import com.example.MyBookShopApp.data.repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorService {

    private JdbcTemplate jdbcTemplate;

    AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository author) {
        this.repository = author;
    }

    @Transactional
    public Map<String, List<Author>> getAuthorsMap() {
        List<Author> authors = repository.findAll();
        Map<String, List<Author>> map = new HashMap<>();
        for (Author a : authors) {
            map.computeIfAbsent(a.getName().substring(0, 1), k -> new ArrayList<>()).add(a);
        }
        return map;
    }

    public Author getAuthorsBySlug(String slug) {
        return repository.findAuthorBySlug(slug);
    }

    public Author getAuthorsById(Integer id) {
        return repository.findAuthorById(id);
    }

    public List<Author> getAuthorsByBook(Integer id) {
        return repository.getAuthorByBook(id);
    }
}
