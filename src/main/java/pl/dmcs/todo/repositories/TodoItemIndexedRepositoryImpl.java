package pl.dmcs.todo.repositories;

import org.hibernate.search.mapper.orm.Search;
import pl.dmcs.todo.model.TodoItemEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TodoItemIndexedRepositoryImpl implements TodoItemIndexedRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TodoItemEntity> search(String query) {
        return Search.session(entityManager)
                .search(TodoItemEntity.class)
                .where(f -> f.match()
                        .fields("name")
                        .matching(query)
                        .fuzzy())
                .fetchAllHits();
    }
}
