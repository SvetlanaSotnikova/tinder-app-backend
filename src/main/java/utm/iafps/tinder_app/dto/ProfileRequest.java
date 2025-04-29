package utm.iafps.tinder_app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import utm.iafps.tinder_app.utils.Country;
import utm.iafps.tinder_app.utils.Gender;

import java.util.List;


@Data
public class ProfileRequest {

    @Min(16)
    private int age;
    @NotNull
    private Gender gender;
    @NotNull
    private Country country;
    @NotNull
    private String city;
    @NotNull
    private List<String> interests;
    @NotNull
    private List<String> hobbies;

    public ProfileRequest(int age, Gender gender, Country country, String city,
                          List<String> interests, List<String> hobbies) {
        this.age = age;
        this.gender = gender;
        this.country = country;
        this.city = city;
        this.interests = interests;
        this.hobbies = hobbies;
    }
}
