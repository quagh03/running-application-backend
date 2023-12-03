package runningEvent.Service;

import runningEvent.Model.EventSession;

import java.util.List;

public interface EventSessionService {
    List<EventSession> getAllEventSession();

    EventSession addEventSession(EventSession eventSession);

    void deleteEventSession(Integer eventSessionId);

    List<EventSession> getEventSessionByStravaId(Long stravaId);
}
