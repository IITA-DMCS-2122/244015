package pl.dmcs.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TodoItemService {

    private final TodoItemRepository todoItemRepository;

    public void addTodoItem(TodoItem todoItem) {
        todoItem.setUuid(UUID.randomUUID().toString());
        todoItemRepository.saveAndFlush(todoItem);
    }

    public TodoItem getTodoItem(String uuid) {
        return todoItemRepository.findByUuid(uuid);
    }

    public List<TodoItem> getTodoItems() {
        return todoItemRepository.findAll();
    }

    public void editTodoItem(TodoItem todoItem) {
        TodoItem temp = todoItemRepository.findByUuid(todoItem.getUuid());
        temp.setName(todoItem.getName());
        temp.setDone(todoItem.isDone());
        todoItemRepository.saveAndFlush(temp);
    }

    public void deleteTodoItem(String uuid) {
        todoItemRepository.deleteByUuid(uuid);
    }
}
