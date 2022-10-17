package list.todo.controller;

import list.todo.dto.SingleResponseDto;
import list.todo.dto.TaskDto;
import list.todo.entity.Task;
import list.todo.mapper.TaskMapper;
import list.todo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public ResponseEntity postTask(@Valid @RequestBody TaskDto.Post taskPostDto) {
        Task task = mapper.taskPostDtoToTask(taskPostDto);
        Task createdTask = taskService.createTask(task);
        TaskDto.Response response = mapper.taskToTaskResponse(createdTask);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity getTasks() {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity getTask(@PathVariable int id) {
//
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity patchTask() {
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteTask() {
//
//    }
//
//    @DeleteMapping
//    public ResponseEntity deleteTasks() {
//
//    }
}
