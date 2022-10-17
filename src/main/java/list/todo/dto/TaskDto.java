package list.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class TaskDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {

        private int id;
        private String title;
        private int order;
        private boolean completed;
    }

    @Getter
    public static class Post {

        @NotBlank(message = "Please write something!")
        private String title;
        private int order;
        private boolean completed;
        private int id;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {

        @NotBlank(message = "Please write something!")
        private String title;
        private int order;
        private boolean completed;
        private int id;
    }
}