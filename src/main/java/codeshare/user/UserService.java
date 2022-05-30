package codeshare.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        codeshare.user.User user = userRepository.findFirstByName(userName)
                                                .orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
        return User.withUsername(user.getName()).password(user.getPassword()).authorities("ROLE_USER").build();
    }
}
