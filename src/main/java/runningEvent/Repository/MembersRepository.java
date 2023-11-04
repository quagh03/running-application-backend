package runningEvent.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import runningEvent.Model.Members;

import java.util.Optional;

public interface MembersRepository extends JpaRepository<Members, Integer> {
    Optional<Members> findByStravaId(Long stravaid);

    Members findByUsername(String username);
}
