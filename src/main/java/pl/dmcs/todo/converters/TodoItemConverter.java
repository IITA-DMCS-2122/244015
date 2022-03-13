package pl.dmcs.todo.converters;

import lombok.experimental.UtilityClass;
import pl.dmcs.todo.dto.TodoItemDto;
import pl.dmcs.todo.model.TodoItemDocument;
import pl.dmcs.todo.model.TodoItemEntity;

import java.util.List;

@UtilityClass
public class TodoItemConverter {

    public TodoItemEntity toEntity(TodoItemDto dto) {
        return TodoItemEntity.builder()
                .uuid(dto.getUuid())
                .name(dto.getName())
                .done(dto.isDone())
                .build();
    }

    public TodoItemDocument toDocument(TodoItemDto dto) {
        return TodoItemDocument.builder()
                .uuid(dto.getUuid())
                .name(dto.getName())
                .done(dto.isDone())
                .build();
    }

    public TodoItemDto toDto(TodoItemDocument document) {
        return TodoItemDto.builder()
                .uuid(document.getUuid())
                .name(document.getName())
                .done(document.isDone())
                .build();
    }

    public List<TodoItemDto> toDtos(List<TodoItemDocument> documents) {
        return documents.stream()
                .map(TodoItemConverter::toDto)
                .toList();
    }
}
