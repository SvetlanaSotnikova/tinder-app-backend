package utm.iafps.tinder_app.services.recommendations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utm.iafps.tinder_app.dto.UserResponse;
import utm.iafps.tinder_app.models.Profile;
import utm.iafps.tinder_app.models.User;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final CosineSimilarityService cosineService;
    private final DbscanClusteringService dbscanService;
    private final CollaborativeFilteringService collaborativeService;

    public List<UserResponse> recommendByCosine(String username) {
        List<User> users = cosineService.recommend(username);
        return users.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<UserResponse> recommendByDbscan(String username) {
        List<User> users = dbscanService.recommend(username);
        return users.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<UserResponse> recommendByCollaborative(String username) {
        List<User> users = collaborativeService.recommend(username);
        return users.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<UserResponse> recommendMerged(String username) {
        Set<User> uniqueUsers = new HashSet<>();

        uniqueUsers.addAll(cosineService.recommend(username));
        uniqueUsers.addAll(dbscanService.recommend(username));
        uniqueUsers.addAll(collaborativeService.recommend(username));

        return uniqueUsers.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private UserResponse mapToResponse(User user) {
        Profile profile = user.getProfile();

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                Optional.ofNullable(profile).map(Profile::getAge).orElse(null),
                Optional.ofNullable(profile).map(Profile::getCity).orElse(null),
                Optional.ofNullable(profile)
                        .map(Profile::getCountry)
                        .map(Enum::name)
                        .orElse(null)
        );
    }


}
