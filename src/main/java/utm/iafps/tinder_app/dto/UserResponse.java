package utm.iafps.tinder_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private Integer age;
    private String city;
    private String country;
}
