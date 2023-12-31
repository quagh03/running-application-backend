package runningEvent.Service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import runningEvent.Model.Activities;
import runningEvent.Repository.ActivitiesRepository;
import runningEvent.Service.ActivitiesService;

import java.util.List;

@Service
public class ActivitiesServiceImpl implements ActivitiesService {
    private final ActivitiesRepository activitiesRepository;

    @Autowired
    public ActivitiesServiceImpl(ActivitiesRepository activitiesRepository){this.activitiesRepository = activitiesRepository;}
    @Override
    public List<Activities> getAllActivities(){return activitiesRepository.findAll();}
    @Override
    public Activities saveActivities(Activities activities){return activitiesRepository.save(activities);}
    @Override
    public List<Activities> saveAllActivities(List<Activities> activitiesList){return activitiesRepository.saveAll(activitiesList);}
    @Override
    public void updateActivities(Integer id, Activities activitiesDetail){
        Activities tempActivity = activitiesRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Activity not exist with id: " + id));

        BeanUtils.copyProperties(activitiesDetail, tempActivity, "id");

        activitiesRepository.save(tempActivity);
    }
    @Override
    public boolean existsByActivitiesStravaId(Long stravaId){return activitiesRepository.existsByActivitiesStravaId(stravaId);}
}
