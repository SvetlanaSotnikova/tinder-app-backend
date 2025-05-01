package utm.iafps.tinder_app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utm.iafps.tinder_app.dto.UserResponse;
import utm.iafps.tinder_app.models.User;
import utm.iafps.tinder_app.security.CustomUserDetails;
import utm.iafps.tinder_app.services.recommendations.RecommendationService;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getRecommendations(@AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        List<UserResponse> recommendations = recommendationService.recommendMerged(user.getUsername());
        return ResponseEntity.ok(recommendations);
    }

    @GetMapping("/cosine")
    public ResponseEntity<List<UserResponse>> recommendCosine(@AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.ok(recommendationService.recommendByCosine(user.getUsername()));
    }

    @GetMapping("/dbscan")
    public ResponseEntity<List<UserResponse>> recommendDbscan(@AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.ok(recommendationService.recommendByDbscan(user.getUsername()));
    }

    @GetMapping("/collaborative")
    public ResponseEntity<List<UserResponse>> recommendCollaborative(@AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.ok(recommendationService.recommendByCollaborative(user.getUsername()));
    }

}
