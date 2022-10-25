package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        if(!Objects.equals(book.getAuthor(), "") || !Objects.equals(book.getTitle(), "") ||book.getSize() != null){
            book.setId(book.hashCode());
            logger.info("store new book: " + book);
            repo.add(book);
        }

    }
    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retreiveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public void removeItemsByRegex(String regex, String field) {
        if (Objects.equals(field, "author")){removeItemsByAuthor(regex);}
        else if (Objects.equals(field, "title")) {removeItemsByTitle(regex);}
        else {removeItemsBySize(regex);}
    }

    private void removeItemsByAuthor(String author) {
        Pattern pattern = Pattern.compile(author);
        logger.info("author" + pattern);
        for (Book book : retreiveAll()) {
            logger.info(book.toString());
            Matcher matcher = pattern.matcher(book.getAuthor());
            if (matcher.matches()) {
                repo.remove(book);
            }
        }
    }

    private void removeItemsByTitle(String title) {
        Pattern pattern = Pattern.compile(title);
        logger.info("title" + pattern);
        for (Book book : retreiveAll()) {
            Matcher matcher = pattern.matcher(book.getTitle());
            if (matcher.matches()) {
                repo.remove(book);
            }
        }
    }

    private void removeItemsBySize(String size) {
        Pattern pattern = Pattern.compile(size);
        logger.info("size" + pattern);
        for (Book book : retreiveAll()) {
            Matcher matcher = pattern.matcher(book.getSize().toString());
            if (matcher.matches()) {
                repo.remove(book);
            }
        }
    }
}
