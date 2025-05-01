package utm.iafps.tinder_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utm.iafps.tinder_app.models.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}
