package pl.dmcs.todo.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class TodoItemDocument {

    @Id
    private String uuid;

    private String name;

    private boolean done;
}
