package pl.dmcs.todo.event;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Document
public class Event<T> implements Serializable {

    @Id
    @Builder.Default
    private String uuid = UUID.randomUUID().toString();

    private T payload;

    private EventAction action;

    @Builder.Default
    private boolean handled = false;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
