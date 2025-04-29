package utm.iafps.tinder_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utm.iafps.tinder_app.models.Like;
import utm.iafps.tinder_app.models.User;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUser(User user);
}
