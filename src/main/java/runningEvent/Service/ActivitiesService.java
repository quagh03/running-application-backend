package runningEvent.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import runningEvent.Model.Activities;
import runningEvent.Repository.ActivitiesRepository;

import java.util.List;

@Service
public class ActivitiesService {
    private final ActivitiesRepository activitiesRepository;

    @Autowired
    public ActivitiesService(ActivitiesRepository activitiesRepository){this.activitiesRepository = activitiesRepository;}

    public List<Activities> getAllActivities(){return activitiesRepository.findAll();}

    public Activities saveActivities(Activities activities){return activitiesRepository.save(activities);}

    public void updateActivities(Integer id, Activities activitiesDetail){
        Activities tempActivity = activitiesRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Activity not exist with id: " + id));

        BeanUtils.copyProperties(activitiesDetail, tempActivity, "id");

        activitiesRepository.save(tempActivity);
    }
}
