package pl.dmcs.todo.repositories;

import pl.dmcs.todo.model.TodoItemEntity;

import java.util.List;

public interface TodoItemIndexedRepository {

    List<TodoItemEntity> search(String query);
}
