package runningEvent.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import runningEvent.Service.RequestService;

import java.util.Optional;

@RestController
public class ActivityController {

  private final RequestService requestService;

  public ActivityController(RequestService requestService) {
    this.requestService = requestService;
  }

//  @GetMapping("/athlete/activities")
//  public ResponseEntity<Object> listAthleteActivities(final OAuth2AuthenticationToken auth) throws Exception {
//      final String url = String.format("https://www.strava.com/api/v3/athlete/activities?");
//
//      String response = requestService.sendGetRequest(auth, url).getBody();
//
//
//      ObjectMapper objectMapper = new ObjectMapper();
//      JsonNode jsonNode = objectMapper.readTree(response);
//
//      String firstname = getNodeText(jsonNode, "firstname");
//      String lastname = getNodeText(jsonNode, "lastname");
//      String city = getNodeText(jsonNode, "city");
//      char sex = getNodeText(jsonNode, "sex").charAt(0);
//      String profile = getNodeText(jsonNode, "profile");
//      Long stravaId = jsonNode.get("id").asLong();
//
//
//  }

    private String getNodeText(JsonNode jsonNode, String fieldName){
        return Optional.ofNullable(jsonNode.get(fieldName)).map(JsonNode::asText).orElse("");
    }
}
