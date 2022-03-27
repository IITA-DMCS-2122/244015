package pl.dmcs.todo.repository.analytics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.todo.entity.analytics.TodoItemAnalyticsEntity;

@Repository
@Transactional
public interface TodoItemAnalyticsRepository extends JpaRepository<TodoItemAnalyticsEntity, Long> {

}
