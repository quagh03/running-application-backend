package runningEvent.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import runningEvent.Model.Activities;
import runningEvent.Service.ActivitiesService;

import java.util.List;

@RestController
public class ActivityController {
  private final ActivitiesService activitiesService;

  public ActivityController(ActivitiesService activitiesService){
    this.activitiesService = activitiesService;
  }

  @GetMapping("/public/activities")
  public List<Activities> getAllActivities(){
    return activitiesService.getAllActivities();
  }
}
