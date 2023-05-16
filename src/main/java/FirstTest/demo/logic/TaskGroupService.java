package FirstTest.demo.logic;

import FirstTest.demo.Model.Task;
import FirstTest.demo.Model.TaskGroup;
import FirstTest.demo.Model.TaskGroupRepository;
import FirstTest.demo.Model.projection.GroupReadModel;
import FirstTest.demo.Model.projection.GroupWriteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class TaskGroupService {
    private TaskGroupRepository repository;

    public TaskGroupService(TaskGroupRepository repository) {
        this.repository = repository;
    }
    public GroupReadModel createGroup(GroupWriteModel source){
     TaskGroup result =  repository.save( source.toGroup());
        return new GroupReadModel(result);
    }



}
