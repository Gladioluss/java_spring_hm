package com.example.MyBookShopApp.data.service;

import com.example.MyBookShopApp.data.book.links.Book2UserEntity;
import com.example.MyBookShopApp.data.entity.Book;
import com.example.MyBookShopApp.data.repo.Books2UserRepository;
import com.example.MyBookShopApp.data.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BooksByPopularService {
    private final Books2UserRepository book2UserRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BooksByPopularService(Books2UserRepository book2UserRepository, BookRepository bookRepository) {
        this.book2UserRepository = book2UserRepository;
        this.bookRepository = bookRepository;
    }

    public Map<Integer, Double> getPopularity(Integer bookId) {
        List<Book2UserEntity> bookList = book2UserRepository.findBook2UserEntitiesByBookId(bookId);
        return bookList.stream().collect(Collectors.groupingBy((Book2UserEntity b)
                        -> b.getBook().getId(),
                Collectors.summingDouble((BooksByPopularService::applyAsDouble))));
    }
    private static double applyAsDouble(Book2UserEntity b) {
        if (b.getType().getId() == 1) {
            return 0.4;
        }
        if (b.getType().getId() == 2) {
            return 0.7;
        }
        if (b.getType().getId() == 3) {
            return 1.0;
        } else {
            return 0.0;
        }
    }
    public void changePopularity(String slug, String cookieName, boolean isPopularity) {
        Book book = bookRepository.findBookBySlug(slug);
        if (cookieName.equals("keptContents")) {
            book.setPopularity(isPopularity ? book.getPopularity() + 0.4 : book.getPopularity() - 0.4);
        } else if (cookieName.equals("cartContents")) {
            book.setPopularity(isPopularity ? book.getPopularity() + 0.7 : book.getPopularity() - 0.7);
        }
        bookRepository.save(book);
    }
}
