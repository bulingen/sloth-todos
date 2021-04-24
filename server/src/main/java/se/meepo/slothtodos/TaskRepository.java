package se.meepo.slothtodos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import se.meepo.slothtodos.model.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {
  List<Task> findByMemberId(Integer memberId);
}
