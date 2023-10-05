package runningEvent.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import runningEvent.Model.Members;
import runningEvent.Repository.MembersRepository;

import java.util.List;

@RestController
public class MembersController {
    private final MembersRepository membersRepository;

    @Autowired
    public MembersController(MembersRepository membersRepository) {
        this.membersRepository = membersRepository;
    }

    @GetMapping("/members")
    public String getAllMembers(){
        return "helloworld";
    }
}
