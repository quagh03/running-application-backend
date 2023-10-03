package runningEvent.StravaAPI;

public class AuthException extends RuntimeException {
    public AuthException(Exception e) {
        super(e);
    }
}
