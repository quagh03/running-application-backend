package runningEvent.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import runningEvent.Model.Members;
import runningEvent.Repository.MembersRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class MembersController {
    private final MembersRepository membersRepository;

    @Autowired
    public MembersController(MembersRepository membersRepository) {
        this.membersRepository = membersRepository;
    }

    @GetMapping("/public/members")
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

    @PostMapping("/public/members")
    public Members addMember(@RequestBody Members members){
        return membersRepository.save(members);
    }
}
