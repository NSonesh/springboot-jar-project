package com.bajaj.jar.model;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public class Task {
    private String id;

    @NotBlank(message = "title is required")
    private String title;

    private boolean completed;

    public Task() {
        this.id = UUID.randomUUID().toString();
    }

    public Task(String title) {
        this();
        this.title = title;
        this.completed = false;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
