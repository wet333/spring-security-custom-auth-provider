package online.awet.learning.customauthprovider.myAuth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AWetAuthentication implements Authentication {

    private String email;
    private String authCode;
    private Object details;
    private Object principal;
    private Collection<GrantedAuthority> authorities;
    private boolean isAuthenticated;

    // before authentication
    AWetAuthentication(String email, String authHeaderCode) {
        this.authCode = authHeaderCode;
        this.email = email;
    }


    // after authentication
    AWetAuthentication(String email, String authCode, Collection<GrantedAuthority> authorities) {
        this.authCode = authCode;
        this.email = email;
        this.authorities = authorities;
    }

    @Override
    public String getName() {
        return this.email;
    }

    @Override
    public String getCredentials() {
        return this.authCode;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }
}
