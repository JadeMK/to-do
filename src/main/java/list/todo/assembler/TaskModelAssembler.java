package list.todo.assembler;

import list.todo.controller.TaskController;
import list.todo.entity.Task;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TaskModelAssembler implements RepresentationModelAssembler<Task, EntityModel<Task>> {

    @Override
    public EntityModel<Task> toModel(Task task) {

        return EntityModel.of(task,
                linkTo(methodOn(TaskController.class).getTask(task.getId())).withSelfRel(),
                linkTo(methodOn(TaskController.class).getTasks()).withRel("tasks"));
    }
}
