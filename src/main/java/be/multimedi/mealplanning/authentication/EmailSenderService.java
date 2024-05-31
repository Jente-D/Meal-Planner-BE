package be.multimedi.mealplanning.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    @Async
    public void sendConfirmationEmail(RegistrationRequest registrationRequest) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(registrationRequest.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("jente.dorssemont@gmail.com");
        mailMessage.setText(
                "To confirm your account, please click here : "
                        +"http://localhost:8080/api/v1/auth/confirm-account?token="
                        + registrationRequest.getConfirmationToken());

        sendEmail(mailMessage);
    }
}
