package utm.iafps.tinder_app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utm.iafps.tinder_app.dto.ProfileRequest;
import utm.iafps.tinder_app.services.ProfileService;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    /**
     * Временная реализация: передаём username через query param,
     * в будущем заменим на получение из JWT
     */
    @PostMapping("/setup")
    public ResponseEntity<String> setupProfile(@RequestParam String username,
                                               @RequestBody @Valid ProfileRequest request) {
        profileService.saveProfile(username, request);
        return ResponseEntity.ok("Profile setup successful");
    }

    @GetMapping
    public ResponseEntity<ProfileRequest> getProfile(@RequestParam String username) {
        ProfileRequest profile = profileService.getProfile(username);
        return ResponseEntity.ok(profile);
    }

}
