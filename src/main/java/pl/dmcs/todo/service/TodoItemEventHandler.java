package pl.dmcs.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.todo.converter.TodoItemAnalyticsEntityConverter;
import pl.dmcs.todo.converter.TodoItemEntityConverter;
import pl.dmcs.todo.dto.TodoItemDto;
import pl.dmcs.todo.entity.primary.TodoItemEntity;
import pl.dmcs.todo.event.Event;
import pl.dmcs.todo.repository.analytics.TodoItemAnalyticsRepository;
import pl.dmcs.todo.repository.event.TodoItemEventRepository;
import pl.dmcs.todo.repository.primary.TodoItemEntityRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoItemEventHandler {

    private static final String QUEUE_NAME = "iita-todo";
    private final TodoItemEventRepository todoItemEventRepository;
    private final TodoItemEntityRepository todoItemEntityRepository;
    private final TodoItemAnalyticsRepository todoItemAnalyticsRepository;

    @RabbitListener(queues = QUEUE_NAME)
    public void handleEvent(Event<TodoItemDto> event) {
        switch (event.getAction()) {
            case ADD -> addTodoItem(event.getPayload());
            case EDIT -> editTodoItem(event.getPayload());
            case DELETE -> deleteTodoItem(event.getPayload());
        }
        event.setHandled(true);
        todoItemEventRepository.save(event);
    }

    private void addTodoItem(TodoItemDto todoItem) {
        todoItemEntityRepository.saveAndFlush(TodoItemEntityConverter.toEntity(todoItem));
        todoItemAnalyticsRepository.saveAndFlush(TodoItemAnalyticsEntityConverter.toEntity(todoItem));
    }

    private void editTodoItem(TodoItemDto todoItem) {
        TodoItemEntity todoItemEntity = todoItemEntityRepository.findByUuid(todoItem.getUuid());
        todoItemEntity.setName(todoItem.getName());
        todoItemEntity.setDone(todoItem.isDone());
    }

    private void deleteTodoItem(TodoItemDto todoItemDto) {
        todoItemEntityRepository.deleteByUuid(todoItemDto.getUuid());
    }
}
