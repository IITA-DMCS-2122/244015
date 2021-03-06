package pl.dmcs.todo.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Indexed
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoItemEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String uuid;

    @FullTextField
    private String name;

    private boolean done;
}
