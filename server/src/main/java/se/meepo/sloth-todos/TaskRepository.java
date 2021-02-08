package se.meepo.recruitment;

import org.springframework.data.repository.CrudRepository;
import se.meepo.recruitment.model.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}
