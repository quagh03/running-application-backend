package runningEvent.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runningEvent.Model.Members;
import runningEvent.Repository.MembersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MembersService {
    private final MembersRepository membersRepository;

    @Autowired
    public MembersService(MembersRepository membersRepository){
        this.membersRepository = membersRepository;
    }

    public List<Members> getAllMembers(){
        return membersRepository.findAll();
    }

    public Optional<Members> getMemberById(Integer id){
        return membersRepository.findById(id);
    }

    public Members saveMember(Members members){
        return membersRepository.save(members);
    }

    public void deleteMemberById(Integer id){
        membersRepository.deleteById(id);
    }
}
