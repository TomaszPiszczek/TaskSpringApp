package FirstTest.demo.Model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name ="task_groups")
public class TaskGroup extends TaskBase {



    public TaskGroup() {
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "group")
    private Set<Task> tasks;



    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }










}
