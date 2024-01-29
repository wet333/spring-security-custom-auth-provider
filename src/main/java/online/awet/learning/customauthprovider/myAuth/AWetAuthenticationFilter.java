package online.awet.learning.customauthprovider.myAuth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AWetAuthenticationFilter extends OncePerRequestFilter {
    private final String codeHeaderName = "X-AWetAuth-Code";
    private final String emailHeaderName = "X-AWetAuth-Email";
    private AuthenticationManager authenticationManager;

    public AWetAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authCode = request.getHeader(codeHeaderName);
        String email = request.getHeader(emailHeaderName);

        if (!StringUtils.isEmpty(authCode) && !StringUtils.isEmpty(email)) {
            // Make the custom Authentication token
            Authentication authToken = new AWetAuthentication(email, authCode);
            // Check if it could be authenticated by any AuthenticationProvider
            Authentication authResult = authenticationManager.authenticate(authToken);
            // Save the result into the SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authResult);
        }

        filterChain.doFilter(request, response);
    }
}
