package pl.dmcs.todo.converters;

import lombok.experimental.UtilityClass;
import pl.dmcs.todo.dto.TodoItemDto;
import pl.dmcs.todo.model.TodoItemDocument;

import java.util.List;

@UtilityClass
public class TodoItemDocumentConverter {

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
                .map(TodoItemDocumentConverter::toDto)
                .toList();
    }
}
