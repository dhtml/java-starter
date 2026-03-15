package com.example.javastarter.service;

import com.example.javastarter.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link TaskService}.
 *
 * <p>These tests do not load a Spring context ({@code @SpringBootTest} is not used),
 * which makes them very fast. We simply instantiate the service directly and call
 * its methods.
 */
class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void findAllReturnsTwoPreloadedTasks() {
        List<Task> tasks = taskService.findAll();
        assertThat(tasks).hasSize(2);
    }

    @Test
    void findByIdReturnsTaskWhenExists() {
        Optional<Task> task = taskService.findById(1L);
        assertThat(task).isPresent();
        assertThat(task.get().getId()).isEqualTo(1L);
    }

    @Test
    void findByIdReturnsEmptyWhenNotFound() {
        Optional<Task> task = taskService.findById(999L);
        assertThat(task).isEmpty();
    }

    @Test
    void createAssignsIdAndAddsTask() {
        Task newTask = new Task();
        newTask.setTitle("New task");
        newTask.setCompleted(false);

        Task saved = taskService.create(newTask);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("New task");
        assertThat(taskService.findAll()).hasSize(3);
    }

    @Test
    void updateModifiesExistingTask() {
        Task update = new Task();
        update.setTitle("Updated");
        update.setCompleted(true);

        Optional<Task> result = taskService.update(1L, update);

        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Updated");
        assertThat(result.get().isCompleted()).isTrue();
    }

    @Test
    void updateReturnsEmptyWhenNotFound() {
        Task update = new Task();
        update.setTitle("Ghost");
        update.setCompleted(false);

        Optional<Task> result = taskService.update(999L, update);

        assertThat(result).isEmpty();
    }

    @Test
    void deleteRemovesTask() {
        boolean removed = taskService.delete(1L);

        assertThat(removed).isTrue();
        assertThat(taskService.findAll()).hasSize(1);
    }

    @Test
    void deleteReturnsFalseWhenNotFound() {
        boolean removed = taskService.delete(999L);

        assertThat(removed).isFalse();
        assertThat(taskService.findAll()).hasSize(2);
    }
}
