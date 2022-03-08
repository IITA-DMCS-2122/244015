package pl.dmcs.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional(propagation = Propagation.NEVER)
public class TodoItemController {

    private final TodoItemService todoItemService;

    @PostMapping("/todo")
    public void addTodoItem(@RequestBody TodoItem todoItem) {
        todoItemService.addTodoItem(todoItem);
    }

    @GetMapping("/todo/{uuid}")
    public TodoItem getTodoItem(@PathVariable String uuid) {
        return todoItemService.getTodoItem(uuid);
    }

    @GetMapping("/todos")
    public List<TodoItem> getTodoItems() {
        return todoItemService.getTodoItems();
    }

    @PutMapping("/todo")
    public void editTodoItem(@RequestBody TodoItem todoItem) {
        todoItemService.editTodoItem(todoItem);
    }

    @DeleteMapping("/todo/{uuid}")
    public void deleteTodoItem(@PathVariable String uuid) {
        todoItemService.deleteTodoItem(uuid);
    }
}
