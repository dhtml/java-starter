package com.example.javastarter.controller;

import com.example.javastarter.model.Task;
import com.example.javastarter.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for {@link TaskController}.
 */
@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TaskService taskService;

    // -----------------------------------------------------------------------
    // GET /tasks
    // -----------------------------------------------------------------------

    @Test
    void getAllTasksReturnsNonEmptyList() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    // -----------------------------------------------------------------------
    // GET /tasks/{id}
    // -----------------------------------------------------------------------

    @Test
    void getTaskByIdReturnsTask() throws Exception {
        // Task with id=1 is created in the TaskService constructor
        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getTaskByIdReturns404WhenNotFound() throws Exception {
        mockMvc.perform(get("/tasks/999"))
                .andExpect(status().isNotFound());
    }

    // -----------------------------------------------------------------------
    // POST /tasks
    // -----------------------------------------------------------------------

    @Test
    void createTaskReturns201WithGeneratedId() throws Exception {
        Task newTask = new Task();
        newTask.setTitle("Write tests");
        newTask.setCompleted(false);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value("Write tests"))
                .andExpect(jsonPath("$.completed").value(false));
    }

    // -----------------------------------------------------------------------
    // PUT /tasks/{id}
    // -----------------------------------------------------------------------

    @Test
    void updateTaskReturnsUpdatedTask() throws Exception {
        // Create a task first so we have a known id to update
        Task created = taskService.create(new Task(null, "Original title", false));

        Task update = new Task();
        update.setTitle("Updated title");
        update.setCompleted(true);

        mockMvc.perform(put("/tasks/" + created.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated title"))
                .andExpect(jsonPath("$.completed").value(true));
    }

    @Test
    void updateTaskReturns404WhenNotFound() throws Exception {
        Task update = new Task();
        update.setTitle("Ghost task");
        update.setCompleted(false);

        mockMvc.perform(put("/tasks/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isNotFound());
    }

    // -----------------------------------------------------------------------
    // DELETE /tasks/{id}
    // -----------------------------------------------------------------------

    @Test
    void deleteTaskReturns204() throws Exception {
        Task created = taskService.create(new Task(null, "Task to delete", false));

        mockMvc.perform(delete("/tasks/" + created.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteTaskReturns404WhenNotFound() throws Exception {
        mockMvc.perform(delete("/tasks/999"))
                .andExpect(status().isNotFound());
    }
}
