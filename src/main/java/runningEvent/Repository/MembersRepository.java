package runningEvent.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import runningEvent.Model.Members;

public interface MembersRepository extends JpaRepository<Members, Integer> {

}
