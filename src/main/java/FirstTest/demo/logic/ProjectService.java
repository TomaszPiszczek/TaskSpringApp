package FirstTest.demo.logic;

import FirstTest.demo.Model.*;
import FirstTest.demo.Model.projection.GroupReadModel;
import FirstTest.demo.Model.projection.GroupTaskReadModel;
import FirstTest.demo.Model.projection.GroupTaskWriteModel;
import FirstTest.demo.Model.projection.GroupWriteModel;
import FirstTest.demo.TaskConfigurationProperties;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectService {
    private ProjectRepository repository;
    private TaskGroupRepository taskGroupRepository;
    private TaskConfigurationProperties config;
    private TaskGroupService service;

    public ProjectService(ProjectRepository repository, TaskGroupRepository taskGroupRepository, TaskConfigurationProperties config, TaskGroupService service) {
        this.repository = repository;
        this.taskGroupRepository = taskGroupRepository;
        this.config = config;
        this.service = service;
    }
    public List<Project> readAllProjects(){
        return repository.findAll();
    }

    public Project save(Project project){
         return repository.save(project);
    }

    public GroupReadModel createGroup(LocalDateTime deadLine,int projectId){
        if(!config.getTemplate().isAllowMultipleTask() && taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
            throw new IllegalStateException("Only one undone group from project is allowed");
        }
        GroupReadModel result = repository.findById(projectId)
                .map(project -> {
                    var targetGroup = new GroupWriteModel();
                    targetGroup.setDescription(project.getDescription());
                    targetGroup.setTasks(
                            project.getSteps().stream()
                                    .map(projectStep -> {
                                        var task = new GroupTaskWriteModel();
                                        task.setDescription(projectStep.getDescription());
                                        task.setDeadline(deadLine.plusDays(projectStep.getDaysToDeadLine()));
                                        return task;
                                    }).collect(Collectors.toSet())
                    );
                    return service.createGroup(targetGroup);
                }).orElseThrow(()->new IllegalArgumentException("Project with given id not found"));
            return result;
    }


}
