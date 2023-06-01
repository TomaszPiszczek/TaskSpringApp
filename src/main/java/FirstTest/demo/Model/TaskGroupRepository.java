package FirstTest.demo.Model;

import java.util.*;

public interface TaskGroupRepository {
    List<TaskGroup> findAll();

    Optional<TaskGroup> findById(int id);

    TaskGroup save(TaskGroup entity);

    boolean existsByDoneIsFalseAndProject_Id(Integer projectId);
    private TaskGroupRepository inMemoryGroupRepository(){
        return new TaskGroupRepository(){
            private int index=0;
            private Map<Integer,TaskGroup> map = new HashMap<>();
            @Override
            public List<TaskGroup> findAll() {
                return new ArrayList<>(map.values());
            }

            @Override
            public Optional<TaskGroup> findById(int id) {
                return Optional.ofNullable(map.get(id));
            }

            @Override
            public TaskGroup save(TaskGroup entity) {

                if(entity.getId()==0)
                {
                    try {
                        TaskGroup.class.getDeclaredField("id").set(entity,++index);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    map.put(entity.getId(),entity);

                }
                return entity;
            }

            @Override
            public boolean existsByDoneIsFalseAndProject_Id(Integer projectId) {
                return false;
            }
        };
    }


}
