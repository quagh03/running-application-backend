package runningEvent.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import runningEvent.Model.Members;
import runningEvent.Repository.MembersRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class MembersController {
    private final MembersRepository membersRepository;

    @Autowired
    public MembersController(MembersRepository membersRepository) {
        this.membersRepository = membersRepository;
    }

    @GetMapping("/members")
    public List<Members> getAllMembers(){
        return membersRepository.findAll();
    }

    @GetMapping("/public/members/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable Integer id){
        try{
            Optional<Members> member = membersRepository.findById(id);
            if(member.isPresent()){
                return ResponseEntity.ok(member.get());
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
