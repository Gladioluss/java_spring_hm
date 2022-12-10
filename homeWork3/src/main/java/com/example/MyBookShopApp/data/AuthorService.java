package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Author> getAuthorByLetter(char letter) {
        return new ArrayList<>(getAuthorsList(new String[]{letter + "%"}));
    }

    public List<Author> getAuthor(Integer id) {
        return new ArrayList<>(getAuthorsList(new Object[]{id}));
    }

    private List<Author> getAuthorsList(Object[] object) {
        return jdbcTemplate.query("SELECT * FROM authors where id=?",
                                                        object,
                                                        (ResultSet rs,
                                                         int rownum) -> {
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setName(rs.getString("name"));
            return author;
        });
    }
}