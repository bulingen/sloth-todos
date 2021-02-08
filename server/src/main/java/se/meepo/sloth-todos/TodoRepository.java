package se.meepo.recruitment;

import org.springframework.data.repository.CrudRepository;
import se.meepo.recruitment.model.Todo;

public interface TodoRepository extends CrudRepository<Todo, Integer> {
}
