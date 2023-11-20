package runningEvent.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import runningEvent.Model.Members;
import runningEvent.Service.MembersService;


@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private MembersService MembersService;
    @PostMapping ("/register/usernamepassword/{memberid}")
    public ResponseEntity<?> updateUsernamePassword(@PathVariable Integer memberid, Members tempMember){
        try{
            String tempUsername = tempMember.getUsername();
            if(!MembersService.getMemberByUsername(tempUsername).isPresent()){
                MembersService.updateMember(memberid, tempMember);
                return new ResponseEntity<>("Updated Username & Password: ", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Username existed ", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
