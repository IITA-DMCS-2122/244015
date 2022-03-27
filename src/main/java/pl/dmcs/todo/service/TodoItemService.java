package pl.dmcs.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.todo.converter.TodoItemAnalyticsEntityConverter;
import pl.dmcs.todo.converter.TodoItemDocumentConverter;
import pl.dmcs.todo.converter.TodoItemEntityConverter;
import pl.dmcs.todo.dto.TodoItemDto;
import pl.dmcs.todo.entity.primary.TodoItemEntity;
import pl.dmcs.todo.repository.analytics.TodoItemAnalyticsRepository;
import pl.dmcs.todo.repository.document.TodoItemDocumentRepository;
import pl.dmcs.todo.repository.primary.TodoItemEntityRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoItemService {

    private final TodoItemEntityRepository todoItemEntityRepository;
    private final TodoItemDocumentRepository todoItemDocumentRepository;
    private final TodoItemAnalyticsRepository todoItemAnalyticsRepository;

    public void addTodoItem(TodoItemDto todoItem) {
        todoItem.setUuid(UUID.randomUUID().toString());
        todoItemEntityRepository.saveAndFlush(TodoItemEntityConverter.toEntity(todoItem));
        todoItemDocumentRepository.save(TodoItemDocumentConverter.toDocument(todoItem));
        todoItemAnalyticsRepository.saveAndFlush(TodoItemAnalyticsEntityConverter.toEntity(todoItem));
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
        TodoItemEntity todoItemEntity = todoItemEntityRepository.findByUuid(todoItem.getUuid());
        todoItemEntity.setName(todoItem.getName());
        todoItemEntity.setDone(todoItem.isDone());
        todoItemDocumentRepository.save(TodoItemDocumentConverter.toDocument(todoItem));
    }

    public void deleteTodoItem(String uuid) {
        todoItemEntityRepository.deleteByUuid(uuid);
        todoItemDocumentRepository.deleteByUuid(uuid);
    }
}
