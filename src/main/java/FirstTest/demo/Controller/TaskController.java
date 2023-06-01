package FirstTest.demo.Controller;

import FirstTest.demo.Model.Task;
import FirstTest.demo.Model.TaskRepository;
import FirstTest.demo.logic.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository repository;
    private final TaskService service;
    public TaskController(TaskRepository repository, TaskService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    CompletableFuture<ResponseEntity<List<Task>>> ReadAllTasks() {

        return service.findAllAsync().thenApply(ResponseEntity::ok);
    }


    @GetMapping
    ResponseEntity<List<Task>> readAllTasks(Pageable page) {

        return ResponseEntity.ok(repository.findAll(page).getContent());
    }
    @GetMapping(value = "/search/done")
    ResponseEntity<List<Task>> readDoneTasks(@RequestParam(defaultValue = "true") boolean state){

        return ResponseEntity.ok(
                repository.findByDone(state)
        );
    }



    @PutMapping(path = "/{id}")
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

    @PostMapping
    ResponseEntity<Task> addTask(@RequestBody Task task) {
        repository.save(task);
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @PatchMapping(path = "/{id}")
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
