package list.todo.service;

import list.todo.entity.Task;
import list.todo.exception.BusinessLogicException;
import list.todo.exception.ExceptionCode;
import list.todo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task createTask(Task task) {

        return taskRepository.save(task);
    }

    public Task findTask(int id) {
        return findVerifiedTask(id);
    }

    public List<Task> findTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Task task) {
        // find the task
        Task foundTask = findVerifiedTask(task.getId());

        // update
        foundTask.setCompleted(task.isCompleted());
        foundTask.setOrder(task.getOrder());
        foundTask.setTitle(task.getTitle());

        return taskRepository.save(foundTask);
    }

    public void deleteTask(int id) {
        Task task = findVerifiedTask(id);
        taskRepository.delete(task);
    }

    public Task findVerifiedTask(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElseThrow(() -> new BusinessLogicException(ExceptionCode.TASK_NOT_FOUND));
    }
}
