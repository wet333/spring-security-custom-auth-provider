package online.awet.learning.customauthprovider.myAuth.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.SecureRandom;

@Getter
@Setter
@Entity
@Table(name = "aw_users")
@NoArgsConstructor
public class AWetUser {

    private static final int TOKEN_LENGTH = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String authToken;
    private String roles;
    private boolean isEnabled;

    public AWetUser(String email, String roles) {
        this.email = email;
        this.roles = roles;
        this.authToken = generateAuthToken();
        this.isEnabled = true;

        System.out.printf("Hi %s!, your login token is: %s\n", email, authToken);
    }

    private static String generateAuthToken() {
        SecureRandom random = new SecureRandom();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder token = new StringBuilder(30);

        for (int i = 0; i < TOKEN_LENGTH; i++) {
            token.append(characters.charAt(random.nextInt(characters.length())));
        }
        return token.toString();
    }
}
