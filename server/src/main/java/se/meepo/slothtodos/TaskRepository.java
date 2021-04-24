package se.meepo.slothtodos;

import org.springframework.data.repository.CrudRepository;
import se.meepo.slothtodos.model.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}
