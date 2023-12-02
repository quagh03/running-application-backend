package runningEvent.Service.Impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import runningEvent.Service.RequestService;

@Service
public class RequestServiceImpl implements RequestService {
    private final OAuth2AuthorizedClientService authClientService;

    public RequestServiceImpl(OAuth2AuthorizedClientService authClientService) {
        this.authClientService = authClientService;
    }

    @Override
    public ResponseEntity<String> sendGetRequest(
            final OAuth2AuthenticationToken auth,
            final String url) {

        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAccessToken(auth));
        final HttpEntity<String> entity =
                new HttpEntity<>("parameters", headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    @Override
    public String getAccessToken(
            final OAuth2AuthenticationToken auth) {
        OAuth2AuthorizedClient client =
            this.authClientService.loadAuthorizedClient(
                auth.getAuthorizedClientRegistrationId(), auth.getName());
//client.getRefreshToken()
        return client.getAccessToken().getTokenValue();
    }

}
