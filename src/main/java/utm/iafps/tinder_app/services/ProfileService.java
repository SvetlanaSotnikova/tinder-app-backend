package utm.iafps.tinder_app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utm.iafps.tinder_app.dto.ProfileRequest;
import utm.iafps.tinder_app.models.Profile;
import utm.iafps.tinder_app.models.User;
import utm.iafps.tinder_app.repositories.ProfileRepository;
import utm.iafps.tinder_app.repositories.UserRepository;
import utm.iafps.tinder_app.utils.Country;
import utm.iafps.tinder_app.utils.Gender;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public void saveProfile(User user, ProfileRequest request) {
        Profile existingProfile = user.getProfile();

        if (existingProfile != null) {
            existingProfile.setAge(request.getAge());
            existingProfile.setGender(request.getGender());
            existingProfile.setCountry(request.getCountry());
            existingProfile.setCity(request.getCity());
            existingProfile.setInterests(request.getInterests());
            existingProfile.setInterests(request.getInterests());
            existingProfile.setAvatarUrl(request.getAvatarUrl());
            profileRepository.save(existingProfile);
        } else {
            Profile profile = Profile.builder()
                    .age(request.getAge())
                    .gender(request.getGender())
                    .country(request.getCountry())
                    .city(request.getCity())
                    .interests(request.getInterests())
                    .hobbies(request.getHobbies())
                    .avatarUrl(request.getAvatarUrl())
                    .user(user)
                    .build();
            profileRepository.save(profile);
        }


    }

    public ProfileRequest getProfile(User user) {
        Profile profile = user.getProfile();
        if (profile == null) {
            throw new RuntimeException("Profile not found");
        }

        return new ProfileRequest(
                profile.getAge(),
                profile.getGender(),
                profile.getCountry(),
                profile.getCity(),
                profile.getInterests(),
                profile.getHobbies(),
                profile.getAvatarUrl()
        );
    }

    public void updateAvatarUrl(User user, String avatarUrl) {
        Profile profile = user.getProfile();
        if (profile == null) throw new RuntimeException("Profile not found");

        profile.setAvatarUrl(avatarUrl);
        profileRepository.save(profile);
    }

}
