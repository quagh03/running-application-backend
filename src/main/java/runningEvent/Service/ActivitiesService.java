package runningEvent.Service;

import runningEvent.Model.Activities;

import java.util.List;

public interface ActivitiesService {
    List<Activities> getAllActivities();

    Activities saveActivities(Activities activities);

    List<Activities> saveAllActivities(List<Activities> activitiesList);

    void updateActivities(Integer id, Activities activitiesDetail);

    boolean existsByActivitiesStravaId(Long stravaId);
}
