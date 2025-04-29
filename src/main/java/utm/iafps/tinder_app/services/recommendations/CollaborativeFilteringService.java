package utm.iafps.tinder_app.services.recommendations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utm.iafps.tinder_app.models.Like;
import utm.iafps.tinder_app.models.User;
import utm.iafps.tinder_app.repositories.LikeRepository;
import utm.iafps.tinder_app.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CollaborativeFilteringService {
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public List<User> recommend(String username) {
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Like> currentUserLikes = likeRepository.findByUser(currentUser);

        Set<Long> currentLikedUserIds = new HashSet<>();
        for (Like like : currentUserLikes) {
            currentLikedUserIds.add(like.getLikedUser().getId());
        }

        List<UserSimilarity> userSimilarities = new ArrayList<>();
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                continue;
            }
            List<Like> otherLikes = likeRepository.findByUser(user);

            Set<Long> otherLikedUserIds = new HashSet<>();
            for (Like like : otherLikes) {
                otherLikedUserIds.add(like.getLikedUser().getId());
            }

            Set<Long> intersection = new HashSet<>(currentLikedUserIds);
            intersection.retainAll(otherLikedUserIds);

            if (!intersection.isEmpty()) {
                userSimilarities.add(new UserSimilarity(user, intersection.size()));
            }
        }

        userSimilarities.sort((a,b) -> Integer.compare(b.similarityScore, a.similarityScore));

        List<User> filteredUsers = new ArrayList<>();
        for (UserSimilarity users : userSimilarities) {
            filteredUsers.add(users.user);
        }

        if (filteredUsers.isEmpty()) {
            for (Like like : currentUserLikes) {
                filteredUsers.add(like.getLikedUser());
            }
        }

        return filteredUsers;
    }

    private static class UserSimilarity {
        User user;
        int similarityScore;

        public UserSimilarity(User user, int similarityScore) {
            this.user = user;
            this.similarityScore = similarityScore;
        }
    }
}
