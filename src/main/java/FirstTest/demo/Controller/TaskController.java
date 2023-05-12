package FirstTest.demo.Controller;

import FirstTest.demo.Model.Task;
import FirstTest.demo.Model.TaskRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.slf4j.LoggerFactory.*;

@Controller
public class TaskController {
    private final TaskRepository repository;

    public static final Logger logger = getLogger(TaskController.class);

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/tasks", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Task>> ReadAllTasks() {
        logger.warn("Exposing all tasks");

        return ResponseEntity.ok(repository.findAll());
    }


    @GetMapping("/tasks")
    ResponseEntity<List<Task>> readAllTasks(Pageable page) {
        logger.warn("Exposing pagable tasks");

        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @PutMapping(path = "/tasks/{id}")
    ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody Task toUpdate) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(task -> {
                    task.updateFrom(toUpdate);
                    repository.save(task);
                });
        return ResponseEntity.noContent().build();


    }

    @PostMapping(value = "/tasks")
    ResponseEntity<Task> addTask(@RequestBody Task task) {
        repository.save(task);
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/tasks/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @PatchMapping(path = "/tasks/{id}")
    public ResponseEntity<?> toggleTask(@PathVariable int id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();

        }
        repository.findById(id)
                .ifPresent(task -> task.setDone(!task.isDone()));

        return ResponseEntity.noContent().build();

        //Find by id
        //Metode post
    }


}
