package utm.iafps.tinder_app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import utm.iafps.tinder_app.services.CloudinaryService;

@RestController
@RequestMapping("/avatar")
@RequiredArgsConstructor
public class AvatarController {
    private final CloudinaryService cloudinaryService;

    @PostMapping
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file) {
        String url = cloudinaryService.uploadAvatar(file);
        return ResponseEntity.ok(url);
    }
}
