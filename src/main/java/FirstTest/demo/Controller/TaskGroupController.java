package FirstTest.demo.Controller;

import FirstTest.demo.Model.Task;
import FirstTest.demo.Model.TaskRepository;
import FirstTest.demo.Model.projection.GroupReadModel;
import FirstTest.demo.Model.projection.GroupWriteModel;
import FirstTest.demo.logic.TaskGroupService;
import FirstTest.demo.logic.TaskService;
import com.electronwill.nightconfig.core.conversion.Path;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/groups")
public class TaskGroupController {
    private final TaskGroupService service;
    private final TaskRepository repository;
    public TaskGroupController(TaskRepository repository, TaskGroupService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping()
   ResponseEntity<List<GroupReadModel>> ReadAllGroups() {

        return ResponseEntity.ok(service.readAll());
    }




    @PostMapping
    ResponseEntity<GroupReadModel> createGroup(@RequestBody GroupWriteModel task) {
        return ResponseEntity.created(URI.create("/")).body(service.createGroup(task));
    }
    @GetMapping(path = "/{id}")
    ResponseEntity<List<Task>> readTasksFromGroup(@PathVariable int id) {

        return ResponseEntity.ok(repository.findAllByGroup_Id(id));
    }



    @Transactional
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> toggleGroup(@PathVariable int id) {
        service.toggleGroup(id);
        return ResponseEntity.noContent().build();

        //Find by id
        //Metode post
    }



}
