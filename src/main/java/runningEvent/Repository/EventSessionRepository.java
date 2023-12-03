package runningEvent.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import runningEvent.Model.EventSession;

import java.util.List;

public interface EventSessionRepository extends JpaRepository<EventSession, Integer> {
    @Query("SELECT es FROM EventSession es JOIN es.activities a WHERE a.stravaId = :stravaId")
    List<EventSession> findByActivities_StravaId(Long stravaId);
}
