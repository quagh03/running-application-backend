package runningEvent.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import runningEvent.Model.Activities;

public interface ActivitiesRepository extends JpaRepository<Activities, Integer> {
}
