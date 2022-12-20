package com.example.MyBookShopApp.data.service;

import com.example.MyBookShopApp.data.entity.Book;
import com.example.MyBookShopApp.data.entity.Author;
import com.example.MyBookShopApp.data.entity.TagEntity;
import com.example.MyBookShopApp.data.entity.GenreEntity;
import com.example.MyBookShopApp.data.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    private final BooksByPopularService booksByPopularService;

    @Autowired
    public BookService(BookRepository bookRepository, BooksByPopularService booksByPopularService) {
        this.bookRepository = bookRepository;
        this.booksByPopularService = booksByPopularService;
    }

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }

    //NEW BOOK SEVICE METHODS

    public List<Book> getBooksByAuthor(String authorName){
        return bookRepository.findBooksByAuthorFirstNameContaining(authorName);
    }

    public List<Book> getBooksByTitle(String title){
        return bookRepository.findBooksByTitleContaining(title);
    }

    public List<Book> getBooksWithPriceBetween(Integer min, Integer max){
        return bookRepository.findBooksByPriceOldBetween(min,max);
    }

    public List<Book> getBooksWithPrice(Integer price){
        return bookRepository.findBooksByPriceOldIs(price);
    }

    public List<Book> getBooksWithMaxPrice(){
        return bookRepository.getBooksWithMaxDiscount();
    }

    public List<Book> getBestsellers(){
        return bookRepository.getBestsellers();
    }

    public Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findAll(nextPage);
    }


    public Page<Book> getPageOfRecommendBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfRecentBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "pubDate"));
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfPubDateBetweenBooks(String from, String to, Integer offset, Integer limit) {
        System.out.println(from + "---" + to);
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "pubDate"));
        try {
            Date dateFrom = new SimpleDateFormat("dd.MM.yyyy").parse(from);
            Date dateTo = new SimpleDateFormat("dd.MM.yyyy").parse(to);
            return bookRepository.findBooksByPubDateBetween(dateFrom, dateTo, nextPage);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Page<Book> getPageOfPopularBooks(Integer offset, Integer limit) {
        addPopularity();
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "popularity"));
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfSearchResultBooks(String wordSearch, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookByTitleContaining(wordSearch, nextPage);
    }

    public void addPopularity() {
        List<Book> bookList = bookRepository.findAll();
        bookList.stream().filter(book ->
                booksByPopularService.getPopularity(book.getId()).get(book.getId()) != null).forEach(book -> {
            book.setPopularity(booksByPopularService.getPopularity(book.getId()).get(book.getId()));
            bookRepository.save(book);
        });
    }

    public Page<Book> getBooksForPageTage(TagEntity tag, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getBookByTag(tag.getSlug(), nextPage);
    }

    public Page<Book> getBooksForPageGenre(GenreEntity genre, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getBookByGenre(genre.getSlug(), nextPage);
    }

    public Page<Book> getBooksForPageAuthor(Author author, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getBookByAuthor(author.getId(), nextPage);
    }

    public Book getBookBySlug(String slug) {
        return bookRepository.findBookBySlug(slug);
    }

    public Integer getNumbersOffAllBooks() {
        return bookRepository.getNumbersOffAllBooks();
    }

    public void save(Book bookToUpdate) {
        bookRepository.save(bookToUpdate);
    }

    public List<Book> getBooksFromCookie(String contents) {
        contents = !contents.startsWith("/") ? contents : contents.substring(1);
        contents = !contents.endsWith("/") ? contents : contents.substring(0, contents.length() - 1);
        String[] cookieSlugs = contents.split("/");
        return bookRepository.findBooksBySlugIn(cookieSlugs);
    }
}
