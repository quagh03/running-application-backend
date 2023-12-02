package runningEvent.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import runningEvent.Model.EventSession;

public interface EventSessionRepository extends JpaRepository<EventSession, Integer> {
}
