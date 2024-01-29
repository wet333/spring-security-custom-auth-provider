package online.awet.learning.customauthprovider.myAuth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.awet.learning.customauthprovider.myAuth.persistence.AWetUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AWetUserDetails implements UserDetails {

    private AWetUser user;

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public String getPassword() {
        return this.user.getAuthToken();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] roleList = this.user.getRoles().split(";");
        return Arrays.stream(roleList).map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.isEnabled();
    }
}
