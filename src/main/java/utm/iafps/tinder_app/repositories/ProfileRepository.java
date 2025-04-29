package utm.iafps.tinder_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utm.iafps.tinder_app.models.Profile;
import utm.iafps.tinder_app.models.User;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
