package online.awet.learning.customauthprovider.configurations;

import online.awet.learning.customauthprovider.myAuth.AWetAuthProvider;
import online.awet.learning.customauthprovider.myAuth.AWetAuthenticationFilter;
import online.awet.learning.customauthprovider.myAuth.AWetUserDetails;
import online.awet.learning.customauthprovider.myAuth.AWetUserDetailsService;
import online.awet.learning.customauthprovider.myAuth.persistence.AWetUser;
import online.awet.learning.customauthprovider.myAuth.persistence.AWetUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private AWetAuthProvider aWetAuthProvider;

    private final UserDetailsService userDetailsService;

    public SecurityConfigurations(AWetUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.authenticationProvider(aWetAuthProvider);
        return builder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/private").authenticated()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().permitAll()
        );
        http.addFilterBefore(new AWetAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    CommandLineRunner commandLineRunner(AWetUserRepository userRepository) {
        return args -> {
            userRepository.save(new AWetUser("roberto@customer.com", "ROLE_CUSTOMER"));
            userRepository.save(new AWetUser("juana@customer.com", "ROLE_CUSTOMER"));
            userRepository.save(new AWetUser("god@customer.com", "ROLE_ADMIN;ROLE_CUSTOMER"));
        };
    }

}
