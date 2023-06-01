package FirstTest.demo.logic;

import FirstTest.demo.Model.TaskGroup;
import FirstTest.demo.Model.TaskGroupRepository;
import FirstTest.demo.Model.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskGroupServiceTest {
    @Test
    @DisplayName("ShouldThrowIllegalArgumentExceptionWhenGroupHasUndoneTasks")
    void toggleGroupTasksAreUnDone() {
        //Given
        var mockTaskReposirory = mock(TaskRepository.class);
        when(mockTaskReposirory.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(true);
        //system under test
        var toTest = new TaskGroupService(null,mockTaskReposirory);
        //when
        var exception = catchThrowable(()->toTest.toggleGroup(1));
        //then
        assertThat(exception).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Group has undone tasks. Done all the tasks first");
    }


    @Test
    @DisplayName("ShouldThrowWhen no group")
    void toogleGroup_wrongId_throwsIllegalArgumentException() {
        //Given
        var mockTaskReposirory = mock(TaskRepository.class);
        when(mockTaskReposirory.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(false);
        //and
        var mockTaskGroupReposirory = mock(TaskGroupRepository.class);
        when(mockTaskGroupReposirory.findById(anyInt())).thenReturn(Optional.empty());
        //
        var toTest = new TaskGroupService(mockTaskGroupReposirory,mockTaskReposirory);
        //when
        var exception = catchThrowable(()->toTest.toggleGroup(1));
        //
        assertThat(exception).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Taskgroup with id not found");

    }
    @Test
    @DisplayName("Should toggle group no group")
    void toogleGroup() {
        //Given
        var mockTaskReposirory = mock(TaskRepository.class);
        when(mockTaskReposirory.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(false);
        //and
        var group = new TaskGroup();
        var beforeToggle = group.isDone();
        //and
        var mockTaskGroupReposirory = mock(TaskGroupRepository.class);
        when(mockTaskGroupReposirory.findById(anyInt())).thenReturn(Optional.of(group));
        //
        var toTest = new TaskGroupService(mockTaskGroupReposirory,mockTaskReposirory);
        //when
        toTest.toggleGroup(1);
        //
       assertThat(group.isDone()).isEqualTo(!beforeToggle);

    }



}