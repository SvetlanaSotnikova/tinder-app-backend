package utm.iafps.tinder_app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import utm.iafps.tinder_app.dto.ProfileRequest;
import utm.iafps.tinder_app.dto.UserResponse;
import utm.iafps.tinder_app.models.Profile;
import utm.iafps.tinder_app.models.User;
import utm.iafps.tinder_app.security.CustomUserDetails;
import utm.iafps.tinder_app.services.ProfileService;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<String> setupProfile(@AuthenticationPrincipal CustomUserDetails userDetails,
                                               @RequestBody @Valid ProfileRequest request) {
        profileService.saveProfile(userDetails.getUser(), request);
        return ResponseEntity.ok("Profile setup successful");
    }

    @GetMapping
    public ResponseEntity<ProfileRequest> getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        ProfileRequest profile = profileService.getProfile(userDetails.getUser());
        return ResponseEntity.ok(profile);
    }



}
