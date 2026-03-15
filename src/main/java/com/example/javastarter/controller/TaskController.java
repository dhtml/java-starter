package com.example.javastarter.controller;

import com.example.javastarter.model.Task;
import com.example.javastarter.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller that exposes CRUD endpoints for the Task resource.
 *
 * <p>Base path: {@code /tasks}
 *
 * <table border="1">
 *   <tr><th>Method</th><th>Path</th><th>Description</th></tr>
 *   <tr><td>GET</td>    <td>/tasks</td>         <td>List all tasks</td></tr>
 *   <tr><td>GET</td>    <td>/tasks/{id}</td>     <td>Get a single task</td></tr>
 *   <tr><td>POST</td>   <td>/tasks</td>          <td>Create a new task</td></tr>
 *   <tr><td>PUT</td>    <td>/tasks/{id}</td>     <td>Update an existing task</td></tr>
 *   <tr><td>DELETE</td> <td>/tasks/{id}</td>     <td>Delete a task</td></tr>
 * </table>
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * Spring automatically injects {@link TaskService} via constructor injection —
     * the recommended approach over field injection ({@code @Autowired} on a field).
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /** GET /tasks – retrieve all tasks. */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    /** GET /tasks/{id} – retrieve a single task by its id. */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /tasks – create a new task.
     *
     * <p>The request body must be JSON, for example:
     * <pre>
     *   {"title": "Learn Spring Boot", "completed": false}
     * </pre>
     *
     * <p>Returns HTTP 201 Created with the saved task (including its generated id).
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task created = taskService.create(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * PUT /tasks/{id} – update an existing task.
     *
     * <p>Returns HTTP 200 OK with the updated task, or HTTP 404 Not Found if the
     * id does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.update(id, task)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE /tasks/{id} – delete a task.
     *
     * <p>Returns HTTP 204 No Content on success, or HTTP 404 Not Found if the
     * id does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
