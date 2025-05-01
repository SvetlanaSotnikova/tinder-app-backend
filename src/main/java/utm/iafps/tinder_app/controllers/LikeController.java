package utm.iafps.tinder_app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import utm.iafps.tinder_app.dto.LikeRequest;
import utm.iafps.tinder_app.dto.LikeResponse;
import utm.iafps.tinder_app.models.Like;
import utm.iafps.tinder_app.models.User;
import utm.iafps.tinder_app.repositories.UserRepository;
import utm.iafps.tinder_app.security.CustomUserDetails;
import utm.iafps.tinder_app.services.LikeService;

import java.util.List;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> toggleLike(@RequestBody @Valid LikeRequest likeRequest,
                                             @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        User likedUser = userRepository.findByUsername(likeRequest.getLikedUsername())
                .orElseThrow(() -> new RuntimeException("Liked user not found"));

        likeService.toggleLike(user, likedUser);
        return ResponseEntity.ok("Like toggled");
    }

    @GetMapping
    public ResponseEntity<List<LikeResponse>> getAllLikes(@AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        List<Like> likes = likeService.getLikes(user);
        List<LikeResponse> responses = likes.stream()
                .map(like -> new LikeResponse(like.getLikedUser().getUsername()))
                .toList();
        return ResponseEntity.ok(responses);
    }
}
