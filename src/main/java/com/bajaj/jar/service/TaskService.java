package com.bajaj.jar.service;

import com.bajaj.jar.model.Task;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TaskService {

    private final Map<String, Task> store = new ConcurrentHashMap<>();

    public List<Task> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Task> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public Task create(String title) {
        Task t = new Task(title);
        store.put(t.getId(), t);
        return t;
    }

    public Optional<Task> update(String id, String title, boolean completed) {
        Task existing = store.get(id);
        if (existing == null) return Optional.empty();
        existing.setTitle(title);
        existing.setCompleted(completed);
        return Optional.of(existing);
    }

    public boolean delete(String id) {
        return store.remove(id) != null;
    }

    public void clear() {
        store.clear();
    }
}
