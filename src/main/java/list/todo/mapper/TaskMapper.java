package list.todo.mapper;

import list.todo.dto.TaskDto;
import list.todo.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task taskPostDtoToTask(TaskDto.Post requestBody);

    TaskDto.Response taskToTaskResponse(Task task);

    List<TaskDto.Response> taskToTaskResponses(List<Task> tasks);

    Task taskPatchToTask(TaskDto.Patch requestBody);
}
