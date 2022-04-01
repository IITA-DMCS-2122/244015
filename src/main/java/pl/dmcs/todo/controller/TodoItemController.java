package pl.dmcs.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.todo.dto.TodoItemDto;
import pl.dmcs.todo.service.TodoItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoItemController {

    private final TodoItemService todoItemService;

    @PostMapping("/todo")
    public void addTodoItem(@RequestBody TodoItemDto todoItem) {
        todoItemService.addTodoItem(todoItem);
    }

    @GetMapping("/todo/{uuid}")
    public TodoItemDto getTodoItem(@PathVariable String uuid) {
        return todoItemService.getTodoItem(uuid);
    }

    @GetMapping("/todos")
    public List<TodoItemDto> getTodoItems() {
        return todoItemService.getTodoItems();
    }

    @GetMapping("/todos/{query}")
    public List<TodoItemDto> searchTodoItems(@PathVariable String query) {
        return todoItemService.searchTodoItems(query);
    }

    @PutMapping("/todo")
    public void editTodoItem(@RequestBody TodoItemDto todoItem) {
        todoItemService.editTodoItem(todoItem);
    }

    @DeleteMapping("/todo/{uuid}")
    public void deleteTodoItem(@PathVariable String uuid) {
        todoItemService.deleteTodoItem(uuid);
    }

    @GetMapping("/todoEventCount")
    public long getTodoItemEventCount() {
        return todoItemService.getTodoItemEventCount();
    }
}
