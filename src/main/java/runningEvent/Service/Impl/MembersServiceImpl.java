package runningEvent.Service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import runningEvent.Model.Members;
import runningEvent.Repository.MembersRepository;
import runningEvent.Service.MembersService;

import java.util.List;
import java.util.Optional;

@Service
public class MembersServiceImpl implements MembersService {
    private final MembersRepository membersRepository;

    @Autowired
    public MembersServiceImpl(MembersRepository membersRepository){
        this.membersRepository = membersRepository;
    }

    @Override
    public List<Members> getAllMembers(){
        return membersRepository.findAll();
    }
    @Override
    public Optional<Members> getMemberById(Integer id){
        return membersRepository.findById(id);
    }
    @Override
    public Members saveMember(Members members){
        return membersRepository.save(members);
    }
    @Override
    public void deleteMemberById(Integer id){
        membersRepository.deleteById(id);
    }
    @Override
    public Members updateMember(Integer id, Members memberDetail){
        Members tempMember = membersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not exist with id: " + id));

        BeanUtils.copyProperties(memberDetail, tempMember, "id");

        return membersRepository.save(tempMember);
    }
    @Override
    public Optional<Members> getMemberByStravaId(Long id){
        return membersRepository.findByStravaId(id);
    }

    @Override
    public Optional<Members> getMemberByUsername(String username) {
        try {
            return Optional.ofNullable(membersRepository.findByUsername(username));
        }catch (Exception e){
            return Optional.empty();
        }
    }
}
