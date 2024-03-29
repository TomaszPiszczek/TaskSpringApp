package FirstTest.demo.adapter;

import FirstTest.demo.Model.TaskGroup;
import FirstTest.demo.Model.TaskGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlTaskGroupRepository extends TaskGroupRepository, JpaRepository<TaskGroup, Integer> {


    @Override
    boolean existsByDoneIsFalseAndProject_Id(Integer projectId);

}
