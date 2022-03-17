package pl.dmcs.todo.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.todo.entity.TodoItemEntity;

@Repository
@Transactional
public interface TodoItemEntityRepository extends JpaRepository<TodoItemEntity, Long>, TodoItemIndexedRepository {

    TodoItemEntity findByUuid(String uuid);
    void deleteByUuid(String uuid);
}
