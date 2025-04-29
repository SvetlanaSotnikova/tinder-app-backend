package utm.iafps.tinder_app.services.recommendations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utm.iafps.tinder_app.models.Profile;
import utm.iafps.tinder_app.models.User;
import utm.iafps.tinder_app.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbscanClusteringService {
    private final UserRepository userRepository;

    public List<User> recommend(String username) {
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile currentProfile = currentUser.getProfile();
        if (currentProfile == null) {
            throw new RuntimeException("Profile not found");
        }

        List<User> allUsers = userRepository.findAll();
        List<User> recommended = new ArrayList<>();

        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {continue;}
            Profile profile = user.getProfile();
            if (profile == null) {continue;}
            if (currentProfile.getCountry().equals(profile.getCountry())) {
                if (currentProfile.getCity() != null
                        && currentProfile.getCity().equals(profile.getCity())) {
                    recommended.add(user);
                } else {
                    recommended.add(user);
                }

            }
        }

        return recommended;
    }
}
