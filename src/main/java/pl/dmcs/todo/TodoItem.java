package pl.dmcs.todo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class TodoItem {

    @Id
    @GeneratedValue
    private Long id;

    private String uuid;

    private String name;

    private boolean done;
}
