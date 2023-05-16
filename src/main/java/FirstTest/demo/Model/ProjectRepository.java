package FirstTest.demo.Model;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    List<Project> findAll();

    Optional<Project> findById(int id);

    Project save(Project entity);

}
