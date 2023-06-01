package FirstTest.demo.logic;

import FirstTest.demo.Controller.LoggerFilter;
import FirstTest.demo.Model.Task;
import FirstTest.demo.Model.TaskRepository;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TaskService {
    Logger logger = LoggerFactory.getLogger(TaskService.class);
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }
    @Async
    public CompletableFuture<List<Task>> findAllAsync(){
        logger.info("Async find!");
       return CompletableFuture.supplyAsync(repository::findAll);
    }
}
