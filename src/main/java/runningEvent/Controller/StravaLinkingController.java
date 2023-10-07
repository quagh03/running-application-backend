package runningEvent.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import runningEvent.Service.StravaLinkingService;

@RestController
public class StravaLinkingController {
    private final StravaLinkingService stravaLinkingService;

    @Autowired
    public StravaLinkingController(StravaLinkingService stravaLinkingService){
        this.stravaLinkingService = stravaLinkingService;
    }

    @RequestMapping("/linkwithstrava")
    public ResponseEntity<Object> linkWithStrava(final OAuth2AuthenticationToken auth){
        try {
            stravaLinkingService.linkWithStrava(auth);
            return ResponseEntity.ok("Added");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred " + e.getMessage());
        }
    }

}
