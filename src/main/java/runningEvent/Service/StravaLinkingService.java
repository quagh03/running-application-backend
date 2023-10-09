package runningEvent.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import runningEvent.Model.Members;
import runningEvent.Repository.MembersRepository;

import java.util.Optional;

@Service
public class StravaLinkingService {
    private final RequestService requestService;
    private final MembersService membersService;

    @Autowired
    public StravaLinkingService(RequestService requestService, MembersService membersService){
        this.requestService = requestService;
        this.membersService = membersService;
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

        Members memberDetail = new Members(firstname, lastname, city, stravaId, sex, profile);

        Optional<Members> tempMember = membersService.getMemberByStravaId(memberDetail.getStravaId());

        if(tempMember.isPresent()){
            Integer id = tempMember.get().getMemberId();
            memberDetail.setMemberId(id);
            membersService.updateMember(id, memberDetail);
        }else{
            membersService.saveMember(memberDetail);
        }


    }

    private String getNodeText(JsonNode jsonNode, String fieldName){
        return Optional.ofNullable(jsonNode.get(fieldName)).map(JsonNode::asText).orElse("");
    }
}
