package utm.iafps.tinder_app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utm.iafps.tinder_app.dto.LikeRequest;
import utm.iafps.tinder_app.dto.LikeResponse;
import utm.iafps.tinder_app.models.Like;
import utm.iafps.tinder_app.services.LikeService;

import java.util.List;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

//    @PostMapping
//    public ResponseEntity<String> likeUser(@RequestBody @Valid LikeRequest likeRequest) {
//        likeService.likeUser(likeRequest.getUsername(), likeRequest.getLikedUsername());
//        return ResponseEntity.ok("Successfully liked user ");
//    }

    @PostMapping
    public ResponseEntity<String> toggleLike(@RequestBody @Valid LikeRequest likeRequest) {
        likeService.toggleLike(likeRequest.getUsername(), likeRequest.getLikedUsername());
        return ResponseEntity.ok("Like toggled");
    }


    @GetMapping
    public ResponseEntity<List<LikeResponse>> getAllLikes(@RequestParam String username) {
        List<Like> likes = likeService.getLikes(username);
        List<LikeResponse> responses = likes.stream()
                .map(like -> new LikeResponse(like.getLikedUser().getUsername()))
                .toList();
        return ResponseEntity.ok(responses);
    }
}
