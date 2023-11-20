package runningEvent.Service;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import runningEvent.Model.Members;

import java.util.Optional;

public interface StravaLinkingService {
    Optional<Members> linkMemberWithStrava(OAuth2AuthenticationToken auth) throws Exception;

    void getActivityStrava(OAuth2AuthenticationToken auth) throws Exception;
}
