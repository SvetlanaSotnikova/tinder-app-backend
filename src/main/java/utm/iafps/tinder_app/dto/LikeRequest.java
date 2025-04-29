package utm.iafps.tinder_app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LikeRequest {
    @NotNull
    private String username;
    @NotNull
    private String likedUsername;
}
