package utm.iafps.tinder_app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
//    @Value("${app.backend-url}")
    private final String backendUrl= "http://localhost:8080";;

    public void sendVerificationEmail(String to, String token) {
        String link = backendUrl + "/auth/verify?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Confirm your email");
        message.setText("Click the link to confirm your email: " + link);
        mailSender.send(message);
    }
}

