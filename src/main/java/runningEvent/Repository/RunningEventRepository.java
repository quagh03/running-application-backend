package runningEvent.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import runningEvent.Model.RunningEvent;

public interface RunningEventRepository extends JpaRepository<RunningEvent, Integer> {
    int countParticipantsByEventId(int eventId);
}
