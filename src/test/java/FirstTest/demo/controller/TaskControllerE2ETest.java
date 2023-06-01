package FirstTest.demo.controller;

import FirstTest.demo.Model.Task;
import FirstTest.demo.Model.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerE2ETest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    TaskRepository repo;

    @Test
    void httpGet_returnsAllTasks(){
        //given
        var inital = repo.findAll().size();
        repo.save(new Task( LocalDateTime.now(),"foo"));
        repo.save(new Task(LocalDateTime.now(),"bar"));
        //when
      Task[] result =  restTemplate.getForObject("http://localhost:" + port + "/tasks",Task[].class);
        //than
        assertThat(result).hasSize(inital+2);
    }

}