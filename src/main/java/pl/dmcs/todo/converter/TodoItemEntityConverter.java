package pl.dmcs.todo.converter;

import lombok.experimental.UtilityClass;
import pl.dmcs.todo.dto.TodoItemDto;
import pl.dmcs.todo.entity.primary.TodoItemEntity;

import java.util.List;

@UtilityClass
public class TodoItemEntityConverter {

    public TodoItemEntity toEntity(TodoItemDto dto) {
        return TodoItemEntity.builder()
                .uuid(dto.getUuid())
                .name(dto.getName())
                .done(dto.isDone())
                .build();
    }

    private TodoItemDto toDto(TodoItemEntity entity) {
        return TodoItemDto.builder()
                .uuid(entity.getUuid())
                .name(entity.getName())
                .done(entity.isDone())
                .build();
    }

    public List<TodoItemDto> toDtos(List<TodoItemEntity> entities) {
        return entities.stream()
                .map(TodoItemEntityConverter::toDto)
                .toList();
    }
}
