package pl.dmcs.todo.repository.primary;

import pl.dmcs.todo.entity.primary.TodoItemEntity;

import java.util.List;

interface TodoItemIndexedRepository {

    List<TodoItemEntity> search(String query);
}
