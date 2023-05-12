package FirstTest.demo.Model;

import java.util.List;
import java.util.Optional;

public interface TaskGroupRepository {
    List<TaskGroup> findAll();
    Optional<TaskGroup> findById(int id);
    TaskGroup save(TaskGroup entity);

}