package runningEvent.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import runningEvent.Service.RequestService;

@RestController
public class ActivityController {

  private final RequestService requestService;

  public ActivityController(RequestService requestService) {
    this.requestService = requestService;
  }

  @GetMapping("/athlete/activities")
  public ResponseEntity<Object> listAthleteActivities(final OAuth2AuthenticationToken auth) {
      final String url = String.format("https://www.strava.com/api/v3/athlete/activities?");

    String response = requestService.sendGetRequest(auth, url).getBody();

    try {
        ObjectMapper objectMapper = new ObjectMapper();
        Object jsonObject = objectMapper.readValue(response, Object.class);

        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }catch(Exception e){
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

    @GetMapping("/athlete")
    public ResponseEntity<Object> listAthlete(final OAuth2AuthenticationToken auth) {

        final String url = String.format("https://www.strava.com/api/v3/athlete");

        String response = requestService.sendGetRequest(auth, url).getBody();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Object jsonObject = objectMapper.readValue(response, Object.class);

            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
