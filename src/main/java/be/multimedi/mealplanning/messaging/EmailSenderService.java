package be.multimedi.mealplanning.messaging;

import be.multimedi.mealplanning.user.PotentialUser;
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
    public void sendConfirmationEmail(PotentialUser potentialUser) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(potentialUser.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("jente.dorssemont@gmail.com");
        mailMessage.setText(
                "To confirm your account, please click here : "
                        +"http://localhost:8080/api/v1/auth/confirm?token="
                        + potentialUser.getConfirmationToken());

        sendEmail(mailMessage);
    }
}
