package runningEvent.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface RequestService {
    ResponseEntity<String> sendGetRequest(
            OAuth2AuthenticationToken auth,
            String url);

    String getAccessToken(
            OAuth2AuthenticationToken auth);
}
