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

import java.util.Optional;

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
    public ResponseEntity<Object> linkWithStrava(final OAuth2AuthenticationToken auth) {
        final String url = "https://www.strava.com/api/v3/athlete";

        try {
            String response = requestService.sendGetRequest(auth, url).getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);

            String firstname = getNodeText(jsonNode, "firstname");
            String lastname = getNodeText(jsonNode, "lastname");
            String city = getNodeText(jsonNode, "city");
            char sex = getNodeText(jsonNode, "sex").charAt(0);
            String profile = getNodeText(jsonNode, "profile");
            Long stravaId = jsonNode.get("id").asLong();

            Members tempMembers = new Members(firstname, lastname, city, stravaId, sex, profile);
            membersRepository.save(tempMembers);

            return ResponseEntity.ok("added");
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    private String getNodeText(JsonNode jsonNode, String fieldName) {
        return Optional.ofNullable(jsonNode.get(fieldName)).map(JsonNode::asText).orElse("");
    }


}
