package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.AuthorService;
import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bookshop")
public class MainPageController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public MainPageController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/main")
    public String mainPage(Model model){
        List<Book> books = bookService.getBooksData();
        Map<Integer, String> map = new HashMap<>();
        books.forEach(book -> map.put(book.getId(), authorService.getAuthor(book.getId()).get(0).getName()));
        model.addAttribute("bookData", books);
        model.addAttribute("authorNames", map);
        return "index";
    }
}
