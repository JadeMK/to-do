package list.todo.controller;

import list.todo.assembler.TaskModelAssembler;
import list.todo.dto.TaskDto;
import list.todo.entity.Task;
import list.todo.mapper.TaskMapper;
import list.todo.service.TaskService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin
@RequestMapping("/")
@RestController
@Validated
public class TaskController {

    private final TaskMapper mapper;
    private final TaskService taskService;

    private final TaskModelAssembler assembler;

    public TaskController(TaskMapper mapper, TaskService taskService, TaskModelAssembler assembler) {
        this.mapper = mapper;
        this.taskService = taskService;
        this.assembler = assembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Task>> postTask(@Valid @RequestBody TaskDto.Post requestBody) {
        Task task = mapper.taskPostDtoToTask(requestBody);
        Task createdTask = taskService.createTask(task);
//        TaskDto.Response response = mapper.taskToTaskResponse(createdTask);

        return new ResponseEntity<>(assembler.toModel(createdTask), HttpStatus.CREATED);
    }

    @GetMapping
    public CollectionModel<Task> getTasks() {
        List<Task> tasks = taskService.findTasks();
//        List<TaskDto.Response> responses = mapper.taskToTaskResponses(tasks);

        return CollectionModel.of(tasks, linkTo(methodOn(TaskController.class).getTasks()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Task>> getTask(@PathVariable int id) {
        Task task = taskService.findTask(id);
//        TaskDto.Response response = mapper.taskToTaskResponse(task);

        return new ResponseEntity<>(assembler.toModel(task), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<Task>> patchTask(@PathVariable int id
            , @Valid @RequestBody TaskDto.Patch requestBody) {

        requestBody.setId(id);
        Task task = mapper.taskPatchToTask(requestBody);
        Task taskUpdated = taskService.updateTask(task);
//        TaskDto.Response response = mapper.taskToTaskResponse(taskUpdated);

        return new ResponseEntity<>(assembler.toModel(taskUpdated), HttpStatus.OK);
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
