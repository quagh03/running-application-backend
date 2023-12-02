package runningEvent.Service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import runningEvent.Model.Activities;
import runningEvent.Model.Members;
import runningEvent.Service.ActivitiesService;
import runningEvent.Service.RequestService;
import runningEvent.Service.StravaLinkingService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StravaLinkingServiceImpl implements StravaLinkingService {
    private final RequestService requestService;
    private final MembersServiceImpl membersService;
    private final ActivitiesService activitiesService;

    @Autowired
    public StravaLinkingServiceImpl(RequestService requestService, MembersServiceImpl membersService, ActivitiesService activitiesService){
        this.requestService = requestService;
        this.membersService = membersService;
        this.activitiesService = activitiesService;
    }

    @Override
    public Optional<Members> linkMemberWithStrava(OAuth2AuthenticationToken auth) throws Exception{
        final String MemberUrl = "https://www.strava.com/api/v3/athlete";

        String response = requestService.sendGetRequest(auth, MemberUrl).getBody();
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
            int id = tempMember.get().getMemberId();
            memberDetail.setMemberId(id);
            return Optional.ofNullable(membersService.updateMember(id, memberDetail));
        }else{
            return Optional.ofNullable(membersService.saveMember(memberDetail));
        }
    }

    @Override
    public void getActivityStrava(OAuth2AuthenticationToken auth) throws Exception{
        final String url = "https://www.strava.com/api/v3/athlete/activities";

        String response = requestService.sendGetRequest(auth, url).getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);

        List<Activities> activitiesList = new ArrayList<>();

        for (JsonNode activity : jsonNode) {
            long stravaId = activity.get("id").asLong();
            if(!activitiesService.existsByActivitiesStravaId(stravaId)){
                Activities newActivity = new Activities();
                newActivity.setActivitiesStravaId(stravaId);
                newActivity.setActivityName(activity.get("name").asText());
                newActivity.setDistance(BigDecimal.valueOf(activity.get("distance").asDouble()));
                newActivity.setActivitiesType(activity.get("type").asText());
                newActivity.setStravaId(activity.get("athlete").get("id").asLong());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                Date startDate = dateFormat.parse(activity.get("start_date").asText());
                newActivity.setStartDate(startDate);

                activitiesList.add(newActivity);
            }
        }
        activitiesService.saveAllActivities(activitiesList);
    }

    private String getNodeText(JsonNode jsonNode, String fieldName){
        return Optional.ofNullable(jsonNode.get(fieldName)).map(JsonNode::asText).orElse("");
    }
}
