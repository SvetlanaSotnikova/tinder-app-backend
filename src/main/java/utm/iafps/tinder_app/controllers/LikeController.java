package utm.iafps.tinder_app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utm.iafps.tinder_app.dto.LikeRequest;
import utm.iafps.tinder_app.services.LikeService;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<String> likeUser(@RequestBody @Valid LikeRequest likeRequest) {
        likeService.likeUser(likeRequest.getUsername(), likeRequest.getLikedUsername());
        return ResponseEntity.ok("Successfully liked user ");
    }
}
