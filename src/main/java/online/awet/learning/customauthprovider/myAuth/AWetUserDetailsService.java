package online.awet.learning.customauthprovider.myAuth;

import online.awet.learning.customauthprovider.myAuth.persistence.AWetUser;
import online.awet.learning.customauthprovider.myAuth.persistence.AWetUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AWetUserDetailsService implements UserDetailsService {
    private final AWetUserRepository repository;

    AWetUserDetailsService(AWetUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // I cant change the name of the method, but it uses the email to load user data from DB
        AWetUser dbUser = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found."));
        return new AWetUserDetails(dbUser);
    }

    public UserDetails loadUserByEmail(String email) {
        return loadUserByUsername(email);
    }
}
