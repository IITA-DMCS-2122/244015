package pl.dmcs.todo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoItemEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String uuid;

    private String name;

    private boolean done;
}
