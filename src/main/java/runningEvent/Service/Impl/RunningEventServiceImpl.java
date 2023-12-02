package runningEvent.Service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runningEvent.Model.RunningEvent;
import runningEvent.Repository.RunningEventRepository;
import runningEvent.Service.RunningEventService;

import java.util.List;
import java.util.Optional;

@Service
public class RunningEventServiceImpl implements RunningEventService {
    @Autowired
    private RunningEventRepository runningEventRepository;

    @Override
    public List<RunningEvent> getAllEvents(){
        try {
            return runningEventRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException("Failed to retrieve running events", e);
        }
    }

    @Override
    public RunningEvent addEvent(RunningEvent runningEvent){
        try {
            return runningEventRepository.save(runningEvent);
        }catch (Exception e){
            throw new RuntimeException("Failed to retrieve running events", e);
        }
    }

    @Override
    public RunningEvent getEventById(Integer eventId){
        try {
            Optional<RunningEvent> event = runningEventRepository.findById(eventId);
            if (event.isPresent()){
                return event.get();
            }else {
                throw new RuntimeException("Failed to retrieve running events");
            }
        }catch (Exception e){
            throw new RuntimeException("Failed to retrieve running events", e);
        }
    }

    @Override
    public void DeleteEvent(Integer eventId){
        if(runningEventRepository.findById(eventId).isPresent()){
            runningEventRepository.deleteById(eventId);
        }else{
            throw new RuntimeException("Failed to retrieve running events");
        }
    }

    @Override
    public RunningEvent editEvent(Integer eventId, RunningEvent newEvent){
        try {
            Optional<RunningEvent> runningEvent = runningEventRepository.findById(eventId);
            if(runningEvent.isPresent()){
                RunningEvent existEvent = runningEvent.get();
                BeanUtils.copyProperties(newEvent, existEvent, "eventId");
                return runningEventRepository.save(existEvent);
            }else{
                throw new RuntimeException("Failed to retrieve running events");
            }
        }catch (Exception e){
            throw new RuntimeException("Failed to retrieve running events");
        }
    }
}
