package runningEvent.Service;

import runningEvent.Model.Members;

import java.util.List;
import java.util.Optional;

public interface MembersService {
    List<Members> getAllMembers();

    Optional<Members> getMemberById(Integer id);

    Members saveMember(Members members);

    void deleteMemberById(Integer id);

    Members updateMember(Integer id, Members memberDetail);

    Optional<Members> getMemberByStravaId(Long id);
    Optional<Members> getMemberByUsername(String username);
}
