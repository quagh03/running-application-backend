package runningEvent.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import runningEvent.Model.Members;
import runningEvent.Repository.MembersRepository;
import runningEvent.Service.RequestService;

@RestController
public class StravaLinkingController {
    private final RequestService requestService;
    private final MembersRepository membersRepository;

    @Autowired
    public StravaLinkingController(RequestService requestService, MembersRepository membersRepository) {
        this.requestService = requestService;
        this.membersRepository = membersRepository;
    }

    @RequestMapping("/linkwithstrava")
    public ResponseEntity<Object> linkWithStrava(final OAuth2AuthenticationToken auth){
        final String url = String.format("https://www.strava.com/api/v3/athlete");

        String response = requestService.sendGetRequest(auth, url).getBody();

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);

            String firstname = jsonNode.get("firstname").asText();
            String lastname = jsonNode.get("lastname").asText();
            String city = jsonNode.get("city").asText();
            char sex = jsonNode.get("sex").asText().charAt(0);
            String profile = jsonNode.get("profile").asText();
            Long StravaId = jsonNode.get("id").asLong();

            Members tempMembers = new Members(firstname, lastname, city, StravaId, sex, profile);

            System.out.println(tempMembers.getFirstname());

            return new ResponseEntity<>(StravaId, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
