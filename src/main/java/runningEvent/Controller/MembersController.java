package runningEvent.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import runningEvent.Model.Members;
import runningEvent.Repository.MembersRepository;
import runningEvent.Service.MembersService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class MembersController {
    private final MembersService membersService;

    @Autowired
    public MembersController(MembersService membersService) {
        this.membersService = membersService;
    }

    @GetMapping("/public/members")
    public List<Members> getAllMembers(){
        return membersService.getAllMembers();
    }

    @GetMapping("/public/members/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable Integer id){
        try{
            Optional<Members> member = membersService.getMemberById(id);
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
        return membersService.saveMember(members);
    }

    @DeleteMapping("/public/members/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        try {
            Optional<Members> member = membersService.getMemberById(id);
            if (member.isPresent()) {
                membersService.deleteMemberById(id);
                return ResponseEntity.ok("Deleted member: " + id);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/public/members/{id}")
    public ResponseEntity<String> updateMember(@PathVariable Integer id, @RequestBody Members memberDetail){
        try{
            membersService.updateMember(id, memberDetail);
            return ResponseEntity.ok("Updated member: " + id);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}
