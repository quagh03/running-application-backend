package runningEvent.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runningEvent.Model.EventSession;
import runningEvent.Repository.EventSessionRepository;
import runningEvent.Service.EventSessionService;

import java.util.List;
import java.util.Optional;

@Service
public class EventSessionServiceImpl implements EventSessionService {
    @Autowired
    private EventSessionRepository eventSessionRepository;

    @Override
    public List<EventSession> getAllEventSession(){
        try {
            return eventSessionRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException("Error" , e);
        }
    }

    @Override
    public EventSession addEventSession(EventSession eventSession){
        try {
            return eventSessionRepository.save(eventSession);
        }catch (Exception e){
            throw new RuntimeException("Error", e);
        }
    }

    @Override
    public void deleteEventSession(Integer eventSessionId){
        try{
            Optional<EventSession> eventSession = eventSessionRepository.findById(eventSessionId);
            if(eventSession.isPresent()){
                eventSessionRepository.deleteById(eventSessionId);
            }
        }catch(Exception e){
            throw new RuntimeException("Error", e);
        }
    }
}
