package pl.dmcs.todo.repository.document;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.todo.document.TodoItemDocument;

@Repository
@Transactional
public interface TodoItemDocumentRepository extends MongoRepository<TodoItemDocument, String> {

    TodoItemDocument findByUuid(String uuid);
    void deleteByUuid(String uuid);
}
