package se.meepo.slothtodos;

import org.springframework.data.repository.CrudRepository;
import se.meepo.slothtodos.model.Todo;

public interface TodoRepository extends CrudRepository<Todo, Integer> {
}
