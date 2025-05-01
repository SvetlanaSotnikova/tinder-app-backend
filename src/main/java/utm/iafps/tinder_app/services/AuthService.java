package utm.iafps.tinder_app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utm.iafps.tinder_app.dto.AuthResponse;
import utm.iafps.tinder_app.dto.LoginRequest;
import utm.iafps.tinder_app.dto.RegisterRequest;
import utm.iafps.tinder_app.models.User;
import utm.iafps.tinder_app.models.VerificationToken;
import utm.iafps.tinder_app.repositories.UserRepository;
import utm.iafps.tinder_app.repositories.VerificationTokenRepository;
import utm.iafps.tinder_app.security.JwtTokenProvider;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final VerificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;

    public void register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(false) // Сразу!
                .build();
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .expiryDate(LocalDateTime.now().plusHours(24))
                .build();

        verificationTokenRepository.save(verificationToken);

//        emailService.sendVerificationEmail(user.getEmail(), token);
    }


    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtTokenProvider.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
}