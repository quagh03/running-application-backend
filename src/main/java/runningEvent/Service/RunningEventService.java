package runningEvent.Service;

import runningEvent.Model.RunningEvent;

import java.util.List;

public interface RunningEventService {
    List<RunningEvent> getAllEvents();

    RunningEvent addEvent(RunningEvent runningEvent);

    RunningEvent getEventById(Integer eventId);

    void DeleteEvent(Integer eventId);

    RunningEvent editEvent(Integer eventId, RunningEvent newEvent);
}
