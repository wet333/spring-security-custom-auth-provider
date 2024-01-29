package online.awet.learning.customauthprovider.myAuth.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AWetUserRepository extends JpaRepository<AWetUser, Long> {
    public Optional<AWetUser> findByEmail(String email);
}