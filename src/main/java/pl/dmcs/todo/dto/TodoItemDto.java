package pl.dmcs.todo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoItemDto {

    private String uuid;
    private String name;
    private boolean done;
}
