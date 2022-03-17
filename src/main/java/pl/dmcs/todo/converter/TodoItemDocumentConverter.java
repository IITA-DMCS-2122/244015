package pl.dmcs.todo.converter;

import lombok.experimental.UtilityClass;
import pl.dmcs.todo.document.TodoItemDocument;
import pl.dmcs.todo.dto.TodoItemDto;

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
