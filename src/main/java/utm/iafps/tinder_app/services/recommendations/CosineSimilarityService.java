package utm.iafps.tinder_app.services.recommendations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utm.iafps.tinder_app.models.Profile;
import utm.iafps.tinder_app.models.User;
import utm.iafps.tinder_app.repositories.UserRepository;
import utm.iafps.tinder_app.utils.CosineSimilarityUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CosineSimilarityService {
    private final UserRepository userRepository;

    public List<User> recommend(String username) {
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile currentProfile = currentUser.getProfile();
        if (currentProfile == null) {
            throw new RuntimeException("Profile not found");
        }

        List<User> allUsers = userRepository.findAll();
        List<UserSimilarity> similarUsers = new ArrayList<>();

        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {continue;}
            Profile profile = user.getProfile();
            if (profile == null) {continue;}

            List<String> currentTerms = merge(currentProfile.getInterests(), currentProfile.getHobbies());
            List<String> otherTerms = merge(profile.getInterests(), profile.getHobbies());

            double similarity = CosineSimilarityUtil.calculateSimilarity(currentTerms, otherTerms);
            if (similarity > 0) {
                similarUsers.add(new UserSimilarity(user, similarity));
            }
        }

        similarUsers.sort((a, b) -> Double.compare(b.similarity, a.similarity));

        List<User> result = new ArrayList<>();
        for (UserSimilarity us : similarUsers) {
            result.add(us.user);
        }

        return result;
    }

    private List<String> merge(List<String> a, List<String> b) {
        List<String> result = new ArrayList<>();
        if (a != null) result.addAll(a);
        if (b != null) result.addAll(b);
        return result;
    }

    private static class UserSimilarity {
        User user;
        double similarity;

        public UserSimilarity(User user, double similarity) {
            this.user = user;
            this.similarity = similarity;
        }
    }
}
