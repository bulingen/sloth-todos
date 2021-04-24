package se.meepo.slothtodos;

import org.springframework.data.repository.CrudRepository;
import se.meepo.slothtodos.model.Member;

public interface MembersRepository extends CrudRepository<Member, Integer> {
}
