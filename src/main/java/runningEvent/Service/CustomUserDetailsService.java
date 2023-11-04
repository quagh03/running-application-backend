package runningEvent.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import runningEvent.Model.Authority;
import runningEvent.Model.Members;
import runningEvent.Repository.MembersRepository;
import runningEvent.Security.CustomUserDetails;

import java.util.ArrayList;
import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {
    private final MembersRepository membersRepository;

    public CustomUserDetailsService(MembersRepository membersRepository){
        this.membersRepository = membersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Members members = membersRepository.findByUsername(username);
        if(members == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        List<Authority> authorities = members.getAuthorities();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(Authority authority : authorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getRole().name()));
        }
        return new CustomUserDetails(members.getUsername(), members.getPassword(),grantedAuthorities);
    }
}
