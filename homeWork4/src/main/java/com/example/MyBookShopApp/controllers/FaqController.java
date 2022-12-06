package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class FaqController {
    private final BookService bookService;

    @Autowired
    public FaqController(BookService bookService) { this.bookService = bookService; }

    @ModelAttribute("booksList")
    public List<Book> bookList() { return bookService.getBooksData(); }

    @GetMapping("/faq")
    public String faqBookPage() { return "faq"; }
}
