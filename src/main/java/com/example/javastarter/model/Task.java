package com.example.javastarter.model;

/**
 * Represents a Task in the task list demo.
 *
 * <p>This is a simple Plain Old Java Object (POJO) that holds task data.
 * In a real application you would typically persist this in a database.
 */
public class Task {

    private Long id;
    private String title;
    private boolean completed;

    public Task() {
    }

    public Task(Long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
