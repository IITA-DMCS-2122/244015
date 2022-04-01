package pl.dmcs.todo.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TodoItemDto implements Serializable {

    private String uuid;
    private String name;
    private boolean done;
}
