package se.meepo.recruitment;

import org.springframework.data.repository.CrudRepository;
import se.meepo.recruitment.model.Member;

public interface MembersRepository extends CrudRepository<Member, Integer> {
}
