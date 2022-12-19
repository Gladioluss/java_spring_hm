package com.example.MyBookShopApp.data.service;

import com.example.MyBookShopApp.data.entity.Author;
import com.example.MyBookShopApp.data.repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {
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
}
