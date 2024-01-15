package tdo.springtodo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDoModel> getTasks(){
        return toDoRepository.findAll();
    }

    public Long count(){
        return toDoRepository.count();
    }

    public void addNewTask(ToDoModel ToDoModel){
        toDoRepository.save(ToDoModel);
    }

    public void addNewTask(String title, boolean done){
        ToDoModel ToDoModel = new ToDoModel(title, done);
        toDoRepository.save(ToDoModel);
    }

    public void deleteTask(Long taskId){
        boolean exists =  toDoRepository.existsById(taskId);
        if(!exists){
            throw new IllegalStateException("Task with id" + taskId + "does not exists");
        }
        toDoRepository.deleteById(taskId);
    }

    public ToDoModel getTaskById(Long id) {
        return toDoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
    }

    public void updateTask(Long id, String title, boolean done) {
        ToDoModel taskToUpdate = toDoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Task with id " + id + " does not exist"));

        taskToUpdate.setTitle(title);
        taskToUpdate.setDone(done);

        toDoRepository.save(taskToUpdate);
    }
}
