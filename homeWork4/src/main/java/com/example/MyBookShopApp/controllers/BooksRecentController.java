package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class BooksRecentController {
    private final BookService bookService;

    @Autowired
    public BooksRecentController(BookService bookService) { this.bookService = bookService; }

    @ModelAttribute("booksList")
    public List<Book> bookList() { return bookService.getBooksData(); }

    @GetMapping("/books/recent")
    public String recentBookPage() { return "books/recent"; }
}
