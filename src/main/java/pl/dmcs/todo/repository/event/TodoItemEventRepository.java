package pl.dmcs.todo.repository.event;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.todo.dto.TodoItemDto;
import pl.dmcs.todo.event.Event;

@Repository
@Transactional
public interface TodoItemEventRepository extends MongoRepository<Event<TodoItemDto>, String> {

}
