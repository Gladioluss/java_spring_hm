package org.example.app.services;

import javax.script.ScriptException;
import java.util.List;

public interface ProjectRepository<T> {
    List<T> retreiveAll();

    void store(T book);

    boolean removeItemById(Integer bookIdToRemove);

    void removeItemsByRegex(String regex, String field) throws ScriptException;
}
