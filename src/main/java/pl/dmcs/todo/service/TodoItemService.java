package pl.dmcs.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.todo.converter.TodoItemEntityConverter;
import pl.dmcs.todo.dto.TodoItemDto;
import pl.dmcs.todo.event.Event;
import pl.dmcs.todo.event.EventAction;
import pl.dmcs.todo.repository.event.TodoItemEventRepository;
import pl.dmcs.todo.repository.primary.TodoItemEntityRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoItemService {

    private static final String QUEUE_NAME = "iita-todo";
    private final RabbitTemplate rabbitTemplate;
    private final TodoItemEventRepository todoItemEventRepository;
    private final TodoItemEntityRepository todoItemEntityRepository;

    public void addTodoItem(TodoItemDto todoItem) {
        todoItem.setUuid(UUID.randomUUID().toString());
        Event<TodoItemDto> event = Event.<TodoItemDto>builder()
                .payload(todoItem)
                .action(EventAction.ADD)
                .build();
        todoItemEventRepository.save(event);
        rabbitTemplate.convertAndSend(QUEUE_NAME, event);
    }

    public TodoItemDto getTodoItem(String uuid) {
        return TodoItemEntityConverter.toDto(todoItemEntityRepository.findByUuid(uuid));
    }

    public List<TodoItemDto> getTodoItems() {
        return TodoItemEntityConverter.toDtos(todoItemEntityRepository.findAll());
    }

    public List<TodoItemDto> searchTodoItems(String query) {
        return TodoItemEntityConverter.toDtos(todoItemEntityRepository.search(query));
    }

    public void editTodoItem(TodoItemDto todoItem) {
        Event<TodoItemDto> event = Event.<TodoItemDto>builder()
                .payload(todoItem)
                .action(EventAction.EDIT)
                .build();
        todoItemEventRepository.save(event);
        rabbitTemplate.convertAndSend(QUEUE_NAME, event);
    }

    public void deleteTodoItem(String uuid) {
        TodoItemDto todoItem = TodoItemDto.builder()
                .uuid(uuid)
                .build();
        Event<TodoItemDto> event = Event.<TodoItemDto>builder()
                .payload(todoItem)
                .action(EventAction.DELETE)
                .build();
        todoItemEventRepository.save(event);
        rabbitTemplate.convertAndSend(QUEUE_NAME, event);
    }

    public long getTodoItemEventCount() {
        return todoItemEventRepository.count();
    }
}
