package FirstTest.demo.logic;

import FirstTest.demo.Model.ProjectRepository;
import FirstTest.demo.Model.TaskGroupRepository;
import FirstTest.demo.Model.TaskRepository;
import FirstTest.demo.TaskConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogicConfiguration {
    @Bean
    ProjectService projectService(
            ProjectRepository reposirory,
            TaskGroupRepository taskGroupRepository,
            TaskConfigurationProperties config) {
        return new ProjectService(reposirory, taskGroupRepository, config, null);
    }

    @Bean
    TaskGroupService taskGroupService(TaskGroupRepository repository, TaskRepository taskRepository){
        return new TaskGroupService(repository,taskRepository);
    }

}
