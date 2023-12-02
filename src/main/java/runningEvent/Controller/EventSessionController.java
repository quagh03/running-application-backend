package runningEvent.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import runningEvent.Model.EventSession;
import runningEvent.Service.EventSessionService;

import java.util.List;

@RestController
public class EventSessionController {
    @Autowired
    private EventSessionService eventSessionService;

    @GetMapping("/public/eventsessions")
    public ResponseEntity<?> getAllEventSession(){
        try {
            return new ResponseEntity<>(eventSessionService.getAllEventSession(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/public/eventsessions")
    public ResponseEntity<?> addEventSession(@RequestBody EventSession eventSession){
        try {
            return new ResponseEntity<>(eventSessionService.addEventSession(eventSession), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/public/eventsessions/{id}")
    public ResponseEntity<?> deleteEventSession(@PathVariable Integer id){
        try{
            eventSessionService.deleteEventSession(id);
            return new ResponseEntity<>("Deleted EventSession id: " + id, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
