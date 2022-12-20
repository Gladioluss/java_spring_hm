package com.example.MyBookShopApp.data.repo;

import com.example.MyBookShopApp.data.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Page<Book> findBooksByTitleContaining(String bookTitle, Pageable nextPage);

    Page<Book> findBooksByPubDateBetween(Date from, Date to, Pageable nextPage);

    @Query(value = "SELECT * FROM books " +
            "inner join book2tag on books.id = book2tag.book_id " +
            "inner join tags on tags.id = book2tag.tag_id " +
            "where tags.slug = ?1", nativeQuery = true)
    Page<Book> getBookByTag(String slug, Pageable pageable);

    @Query(value = "SELECT * FROM books " +
            "inner join book2genre on books.id = book2genre.book_id " +
            "inner join genre on genre.id = book2genre.genre_id " +
            "where genre.slug = ?1", nativeQuery = true)
    Page<Book> getBookByGenre(String slug, Pageable nextPage);

    @Query(value = "SELECT * FROM books " +
            "inner join book2author on books.id = book2author.book_id " +
            "inner join authors on authors.id = book2author.author_id " +
            "where authors.id = ?1 order by books.pub_date desc", nativeQuery = true)
    Page<Book> getBookByAuthor(Integer id, Pageable nextPage);

    Book findBookBySlug(String slug);

    @Query(value = "SELECT count(id) from books", nativeQuery = true)
    Integer getNumbersOffAllBooks();

    List<Book> findBooksBySlugIn(String[] slugs);

    List<Book> findBooksByAuthor_FirstName(String name);

    @Query("from Book")
    List<Book> customFindAllBooks();

    //NEW BOOK REST REPOSITORY COMMANDS

    List<Book> findBooksByAuthorFirstNameContaining(String authorFirstName);

    List<Book> findBooksByTitleContaining(String bookTitle);

    List<Book> findBooksByPriceOldBetween(Integer min, Integer max);

    List<Book> findBooksByPriceOldIs(Integer price);

    @Query("from Book where isBestseller=1")
    List<Book> getBestsellers();

    @Query(value = "SELECT * FROM books WHERE discount = (SELECT MAX(discount) FROM books)", nativeQuery = true)
    List<Book> getBooksWithMaxDiscount();

    Page<Book> findBookByTitleContaining(String bookTitle, Pageable nextPage);
}
