package pl.dmcs.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

    TodoItem findByUuid(String uuid);
    void deleteByUuid(String uuid);
}
