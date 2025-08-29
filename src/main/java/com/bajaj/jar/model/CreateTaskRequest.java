package com.bajaj.jar.model;

import jakarta.validation.constraints.NotBlank;

public class CreateTaskRequest {
    @NotBlank(message = "title is required")
    private String title;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
