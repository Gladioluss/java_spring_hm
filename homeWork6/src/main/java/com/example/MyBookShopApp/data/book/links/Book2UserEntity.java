package com.example.MyBookShopApp.data.book.links;

import com.example.MyBookShopApp.data.entity.Book;
import com.example.MyBookShopApp.data.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book2user")
public class Book2UserEntity {

    @EmbeddedId
    private Key2User id;

    @Column(columnDefinition = "DATE NOT NULL")
    private LocalDateTime time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    @JsonManagedReference
    @JsonIgnore
    private Book2UserTypeEntity type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    @JsonIgnore
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity user;


    public Key2User getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Book2UserTypeEntity getType() {
        return type;
    }

    public Book getBook() {
        return book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setId(Key2User id) {
        this.id = id;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setType(Book2UserTypeEntity type) {
        this.type = type;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
