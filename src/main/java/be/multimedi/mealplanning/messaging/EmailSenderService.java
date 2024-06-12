package be.multimedi.mealplanning.messaging;

import be.multimedi.mealplanning.user.PotentialUser;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
    public void sendConfirmationEmail(PotentialUser potentialUser) throws MessagingException {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
        helper.setTo(potentialUser.getEmail());
        helper.setSubject("Complete Registration!");
        helper.setFrom("jente.dorssemont@gmail.com");
        String htmlMsg =  "<div style='font-family: Arial, sans-serif; line-height: 1.6;'>"
                + "<img src='https://drive.google.com/file/d/1A4u9rZXB-nULXJgZLrB1IDqsZmTUubcN/view?usp=sharing' alt='NeutriTech Logo' style='width: 150px; margin-bottom: 20px;' />"
                + "<h1 style='color: #5e935e;'>Welcome to NeutriTech!</h1>"
                + "<p>Thank you for registering with NeutriTech. To complete your registration and activate your account, please confirm your email address by clicking the button below:</p>"
                + "<p style='text-align: center; margin: 20px 0;'>"
                + "<a href='http://localhost:8080/api/v1/auth/confirm?token=" + potentialUser.getConfirmationToken() + "' class='btn-primary' style='display: inline-block; padding: 10px 20px; border-radius: 1rem; color: white; background-color: #5e935e; border: none; text-decoration: none; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);'>Confirm Your Account</a>"
                + "</p>"
                + "<p>If you did not sign up for a NeutriTech account, please ignore this email.</p>"
                + "<p>Best regards,<br/>The NeutriTech Team</p>"
                + "</div>";
        helper.setText(htmlMsg, true);

        javaMailSender.send(mailMessage);
    }

//    @Async
//    public void sendEmail(SimpleMailMessage email) {
//        javaMailSender.send(email);
//    }
//
//    @Async
//    public void sendConfirmationEmail(PotentialUser potentialUser) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(potentialUser.getEmail());
//        mailMessage.setSubject("Complete Registration!");
//        mailMessage.setFrom("jente.dorssemont@gmail.com");
//        mailMessage.setText(
//                "To confirm your account, please click here : "
//                        +"http://localhost:8080/api/v1/auth/confirm?token="
//                        + potentialUser.getConfirmationToken());
//
//        sendEmail(mailMessage);
//    }
}
