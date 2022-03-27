package pl.dmcs.todo.converter;

import lombok.experimental.UtilityClass;
import pl.dmcs.todo.dto.TodoItemDto;
import pl.dmcs.todo.entity.analytics.TodoItemAnalyticsEntity;

@UtilityClass
public class TodoItemAnalyticsEntityConverter {

    public TodoItemAnalyticsEntity toEntity(TodoItemDto dto) {
        return TodoItemAnalyticsEntity.builder()
                .uuid(dto.getUuid())
                .name(dto.getName())
                .done(dto.isDone())
                .build();
    }
}
