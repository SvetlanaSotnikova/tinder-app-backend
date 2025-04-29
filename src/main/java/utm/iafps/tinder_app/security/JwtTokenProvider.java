package utm.iafps.tinder_app.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    public String generateToken(String username) {
        return "dummy-jwt-token-for-" + username;
    }
}
