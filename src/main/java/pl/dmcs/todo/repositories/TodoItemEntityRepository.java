package pl.dmcs.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.todo.model.TodoItemEntity;

@Repository
@Transactional
public interface TodoItemEntityRepository extends JpaRepository<TodoItemEntity, Long> {

    TodoItemEntity findByUuid(String uuid);
    void deleteByUuid(String uuid);
}
