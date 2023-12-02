package runningEvent.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import runningEvent.Model.RunningEvent;
import runningEvent.Service.RunningEventService;

import java.util.List;

@RestController
public class RunningEventController {
    @Autowired
    private RunningEventService runningEventService;

    @GetMapping("/public/events")
    public ResponseEntity<?> getAllEvents(){
        try {
            List<RunningEvent> runningEventsList = runningEventService.getAllEvents();
            return new ResponseEntity<>(runningEventsList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/public/events/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Integer id){
        try {
            return new ResponseEntity<>(runningEventService.getEventById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/public/events/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        try {
            runningEventService.DeleteEvent(id);
            return new ResponseEntity<>("Deleted event with id: " + id, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/public/events/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Integer id, @RequestBody RunningEvent newEvent){
        try {
            return new ResponseEntity<>(runningEventService.editEvent(id,newEvent), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/public/events")
    public ResponseEntity<?> addEvent(@RequestBody RunningEvent runningEvent){
        try {
            return new ResponseEntity<>(runningEventService.addEvent(runningEvent), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
