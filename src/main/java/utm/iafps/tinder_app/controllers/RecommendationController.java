package utm.iafps.tinder_app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utm.iafps.tinder_app.dto.UserResponse;
import utm.iafps.tinder_app.services.recommendations.RecommendationService;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getRecommendations(@RequestParam String username) {
        List<UserResponse> recommendations = recommendationService.recommendMerged(username);
        return ResponseEntity.ok(recommendations);
    }

    @GetMapping("/cosine")
    public ResponseEntity<List<UserResponse>> recommendCosine(@RequestParam String username) {
        return ResponseEntity.ok(recommendationService.recommendByCosine(username));
    }

    @GetMapping("/dbscan")
    public ResponseEntity<List<UserResponse>> recommendDbscan(@RequestParam String username) {
        return ResponseEntity.ok(recommendationService.recommendByDbscan(username));
    }

    @GetMapping("/collaborative")
    public ResponseEntity<List<UserResponse>> recommendCollaborative(@RequestParam String username) {
        return ResponseEntity.ok(recommendationService.recommendByCollaborative(username));
    }

}
