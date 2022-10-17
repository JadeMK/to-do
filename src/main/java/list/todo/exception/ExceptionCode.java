package list.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ExceptionCode {

    TASK_NOT_FOUND(404, "Task not found"),
    TASK_EXIST(409, "Task exists");

    @Getter
    private int status;

    @Getter
    private String message;
}
