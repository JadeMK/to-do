package list.todo.controller;

import list.todo.dto.MultiResponseDto;
import list.todo.dto.SingleResponseDto;
import list.todo.dto.TaskDto;
import list.todo.entity.Task;
import list.todo.mapper.TaskMapper;
import list.todo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/")
@RestController
@Validated
public class TaskController {

    private final TaskMapper mapper;
    private final TaskService taskService;

    public TaskController(TaskMapper mapper, TaskService taskService) {
        this.mapper = mapper;
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity postTask(@Valid @RequestBody TaskDto.Post requestBody) {
        Task task = mapper.taskPostDtoToTask(requestBody);
        Task createdTask = taskService.createTask(task);
        TaskDto.Response response = mapper.taskToTaskResponse(createdTask);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getTasks() {
        List<Task> tasks = taskService.findTasks();
        List<TaskDto.Response> responses = mapper.taskToTaskResponses(tasks);

        return new ResponseEntity<>(new MultiResponseDto<>(responses), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTask(@PathVariable int id) {
        Task task = taskService.findTask(id);
        TaskDto.Response response = mapper.taskToTaskResponse(task);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchTask(@PathVariable int id
            , @Valid @RequestBody TaskDto.Patch requestBody) {

        requestBody.setId(id);
        Task task = mapper.taskPatchToTask(requestBody);
        Task taskUpdated = taskService.updateTask(task);
        TaskDto.Response response = mapper.taskToTaskResponse(taskUpdated);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteTasks() {
        taskService.deleteTasks();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
