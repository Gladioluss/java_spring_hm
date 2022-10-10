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
    public void removeItemsByRegex(String regex, String field) throws ScriptException {
        Pattern pattern = Pattern.compile(regex);
        if (Objects.equals(field, "author")){removeBooks(pattern, "book.getAuthor()");}
        else if (Objects.equals(field, "title")) {removeBooks(pattern, "book.getTitle()");}
        else {removeBooks(pattern, "book.getSize");}
    }

    private void removeBooks(Pattern pattern, String method) throws ScriptException {
        for (Book book : retreiveAll()) {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("java");
            Matcher matcher = pattern.matcher(engine.eval(method).toString());
            if (matcher.matches()) {
                repo.remove(book);
            }
        }
    }
}
