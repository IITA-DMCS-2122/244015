package pl.dmcs.todo.entity.analytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class TodoItemAnalyticsEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String uuid;

    private String name;

    private boolean done;

    @CreatedDate
    private LocalDateTime createdDate;
}
