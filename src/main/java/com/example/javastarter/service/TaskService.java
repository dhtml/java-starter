package com.example.javastarter.service;

import com.example.javastarter.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service layer for managing tasks.
 *
 * <p>This service uses an in-memory list to store tasks so that the demo
 * works without any database setup. In a real application you would inject
 * a repository (e.g., a JPA {@code TaskRepository}) and delegate to it here.
 *
 * <p>The {@code @Service} annotation tells Spring to create a single instance
 * of this class and make it available for injection with {@code @Autowired} or
 * constructor injection.
 */
@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    public TaskService() {
        // Pre-load a couple of sample tasks so the demo is not empty on startup
        tasks.add(new Task(idSequence.getAndIncrement(), "Buy groceries", false));
        tasks.add(new Task(idSequence.getAndIncrement(), "Read a Spring Boot tutorial", true));
    }

    /** Returns all tasks. */
    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    /** Returns the task with the given id, or an empty Optional if not found. */
    public Optional<Task> findById(Long id) {
        return tasks.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    /**
     * Creates a new task.
     *
     * @param task task data (id will be assigned automatically)
     * @return the saved task with its generated id
     */
    public Task create(Task task) {
        task.setId(idSequence.getAndIncrement());
        tasks.add(task);
        return task;
    }

    /**
     * Updates an existing task.
     *
     * @param id   id of the task to update
     * @param task updated task data
     * @return the updated task, or an empty Optional if not found
     */
    public Optional<Task> update(Long id, Task task) {
        return findById(id).map(existing -> {
            existing.setTitle(task.getTitle());
            existing.setCompleted(task.isCompleted());
            return existing;
        });
    }

    /**
     * Deletes the task with the given id.
     *
     * @return {@code true} if a task was removed, {@code false} if not found
     */
    public boolean delete(Long id) {
        return tasks.removeIf(t -> t.getId().equals(id));
    }
}
