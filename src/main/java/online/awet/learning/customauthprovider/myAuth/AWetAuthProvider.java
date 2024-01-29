package online.awet.learning.customauthprovider.myAuth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AWetAuthProvider implements AuthenticationProvider {
    private final AWetUserDetailsService aWetUserDetailsService;

    public AWetAuthProvider(AWetUserDetailsService aWetUserDetailsService) {
        this.aWetUserDetailsService = aWetUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // Cast the auth-request into my custom Authentication token type
        AWetAuthentication token = (AWetAuthentication) authentication;

        try {
            // Actually a AWetUserDetails instance
            UserDetails user = aWetUserDetailsService.loadUserByEmail(token.getName());
            boolean isValidEmail = user.getUsername().equals(token.getName());
            boolean isValidCredentials = isValidEmail && user.getPassword().equals(token.getCredentials());

            token = new AWetAuthentication(user.getUsername(), user.getPassword(), (Collection<GrantedAuthority>) user.getAuthorities());
            token.setAuthenticated(isValidCredentials);
        } catch (UsernameNotFoundException ex) {
            token.setAuthenticated(false);
        }
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AWetAuthentication.class.isAssignableFrom(authentication);
    }
}
