package FirstTest.demo.adapter;

import FirstTest.demo.Model.Task;
import FirstTest.demo.Model.TaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlTaskRepository extends TaskRepository,JpaRepository<Task,Integer> {
    @Override
    @Query(nativeQuery = true, value = "SELECT COUNT(*) > 0 from tasks where id=:id ")
    boolean existsById(@Param("id") Integer id);
    @Override
    boolean existsByDoneIsFalseAndGroup_Id(Integer groupId);

    @Override
    List<Task> findAllByGroup_Id(Integer groupId);
}
