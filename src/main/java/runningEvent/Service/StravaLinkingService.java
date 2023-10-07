package runningEvent.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import runningEvent.Model.Members;
import runningEvent.Repository.MembersRepository;

import java.util.Optional;

@Service
public class StravaLinkingService {
    private final RequestService requestService;
    private final MembersRepository membersRepository;

    @Autowired
    public StravaLinkingService(RequestService requestService, MembersRepository membersRepository){
        this.requestService = requestService;
        this.membersRepository = membersRepository;
    }

    public void linkWithStrava(OAuth2AuthenticationToken auth) throws Exception{
        final String url = "https://www.strava.com/api/v3/athlete";

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
    }

    private String getNodeText(JsonNode jsonNode, String fieldName){
        return Optional.ofNullable(jsonNode.get(fieldName)).map(JsonNode::asText).orElse("");
    }
}
