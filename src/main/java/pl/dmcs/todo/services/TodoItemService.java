package pl.dmcs.todo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.todo.converters.TodoItemDocumentConverter;
import pl.dmcs.todo.converters.TodoItemEntityConverter;
import pl.dmcs.todo.dto.TodoItemDto;
import pl.dmcs.todo.model.TodoItemEntity;
import pl.dmcs.todo.repositories.TodoItemDocumentRepository;
import pl.dmcs.todo.repositories.TodoItemEntityRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoItemService {

    private final TodoItemEntityRepository todoItemEntityRepository;
    private final TodoItemDocumentRepository todoItemDocumentRepository;

    public void addTodoItem(TodoItemDto todoItem) {
        todoItem.setUuid(UUID.randomUUID().toString());
        todoItemEntityRepository.saveAndFlush(TodoItemEntityConverter.toEntity(todoItem));
        todoItemDocumentRepository.save(TodoItemDocumentConverter.toDocument(todoItem));
    }

    public TodoItemDto getTodoItem(String uuid) {
        return TodoItemDocumentConverter.toDto(todoItemDocumentRepository.findByUuid(uuid));
    }

    public List<TodoItemDto> getTodoItems() {
        return TodoItemDocumentConverter.toDtos(todoItemDocumentRepository.findAll());
    }

    public List<TodoItemDto> searchTodoItems(String query) {
        return TodoItemEntityConverter.toDtos(todoItemEntityRepository.search(query));
    }

    public void editTodoItem(TodoItemDto todoItem) {
        TodoItemEntity temp = todoItemEntityRepository.findByUuid(todoItem.getUuid());
        temp.setName(todoItem.getName());
        temp.setDone(todoItem.isDone());
        todoItemDocumentRepository.save(TodoItemDocumentConverter.toDocument(todoItem));
    }

    public void deleteTodoItem(String uuid) {
        todoItemEntityRepository.deleteByUuid(uuid);
        todoItemDocumentRepository.deleteByUuid(uuid);
    }
}
