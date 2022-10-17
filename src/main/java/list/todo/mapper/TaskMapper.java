package list.todo.mapper;

import list.todo.dto.TaskDto;
import list.todo.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task taskPostDtoToTask(TaskDto.Post requestBody);

    TaskDto.Response taskToTaskResponse(Task task);

    // TODO: Patch, Response
}
