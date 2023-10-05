package runningEvent.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import runningEvent.Model.Members;

public interface MembersRepository extends JpaRepository<Members, Integer> {
}
