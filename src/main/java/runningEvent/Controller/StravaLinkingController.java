package runningEvent.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import runningEvent.Service.StravaLinkingService;
import runningEvent.Service.Impl.StravaLinkingServiceImpl;

@RestController
public class StravaLinkingController {
    private final StravaLinkingService stravaLinkingService;

    @Autowired
    public StravaLinkingController(StravaLinkingServiceImpl stravaLinkingServiceImpl){
        this.stravaLinkingService = stravaLinkingServiceImpl;
    }

    @RequestMapping("/linkwithstrava")
    public RedirectView linkMemberWithStrava(final OAuth2AuthenticationToken auth){
        try {
            stravaLinkingService.linkMemberWithStrava(auth);
        }catch (Exception e){
            return new RedirectView("/error");
        }
        return new RedirectView("/linkactivities");
    }

    @RequestMapping("/linkactivities")
    public ResponseEntity<Object> linkActivitiesWithStrava(final OAuth2AuthenticationToken auth){
        try {
            stravaLinkingService.getActivityStrava(auth);
            return ResponseEntity.ok("Linked");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred " + e.getMessage());
        }
    }

}
