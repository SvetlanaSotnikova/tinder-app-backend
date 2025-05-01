package utm.iafps.tinder_app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utm.iafps.tinder_app.models.Like;
import utm.iafps.tinder_app.models.User;
import utm.iafps.tinder_app.repositories.LikeRepository;
import utm.iafps.tinder_app.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    public void likeUser(User user, String likedUsername) {
        User likedUser = userRepository.findByUsername(likedUsername)
                .orElseThrow(() -> new RuntimeException("Liked user not found"));

        Like like = Like.builder()
                .user(user)
                .likedUser(likedUser)
                .build();

        likeRepository.save(like);
    }

    public List<Like> getLikes(User user) {
        return likeRepository.findByUser(user);
    }

    public void toggleLike(User user, User likedUser) {

        Like existingLike = likeRepository.findByUserAndLikedUser(user, likedUser);
        if (existingLike != null) {
            likeRepository.delete(existingLike);
        } else {
            Like like = Like.builder()
                    .user(user)
                    .likedUser(likedUser)
                    .build();
            likeRepository.save(like);
        }

    }
}
