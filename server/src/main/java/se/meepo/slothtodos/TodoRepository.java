package se.meepo.slothtodos;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import se.meepo.slothtodos.model.Todo;

public interface TodoRepository extends CrudRepository<Todo, Integer> {
    @EntityGraph(attributePaths = {"tasks"})
    List<Todo> findAll();
}
