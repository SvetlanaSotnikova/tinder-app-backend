package utm.iafps.tinder_app.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import utm.iafps.tinder_app.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
