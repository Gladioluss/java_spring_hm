package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.entity.Author;
import com.example.MyBookShopApp.data.service.AuthorService;
import com.example.MyBookShopApp.data.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@Api(description = "authors data")
public class AuthorsController {

    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AuthorsController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @ModelAttribute("authorsMap")
    public Map<String,List<Author>> authorsMap(){
        return authorService.getAuthorsMap();
    }

    @GetMapping("/authors")
    public String authorsPage(Model model){
        model.addAttribute("authorsMap", authorService.getAuthorsMap());
        return "/authors/index";
    }

    @GetMapping("/authors/{slug}")
    public String authorPage(@PathVariable("slug") String slug, Model model) {
        Author author = authorService.getAuthorsBySlug(slug);
        model.addAttribute("authorSlug", author);
        model.addAttribute("authorBooks", bookService.getBooksForPageAuthor(author, 0, 5).getContent());
        return "authors/slug";
    }

    @GetMapping("/books/author/{slug}")
    public String authorBooksPage(@PathVariable("slug") String slug, Model model) {
        Author author = authorService.getAuthorsBySlug(slug);
        model.addAttribute("authorSlug", author);
        model.addAttribute("authorBooks", bookService.getBooksForPageAuthor(author, 0, 20).getContent());
        return "books/author";
    }

    @ApiOperation("method to get map of authors")
    @GetMapping("/api/authors")
    @ResponseBody
    public Map<String,List<Author>> authors(){
        return authorService.getAuthorsMap();
    }
}
