package be.multimedi.mealplanning.registration;

import be.multimedi.mealplanning.authentication.UserRegistrationDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class UserRegistrationRequestDto extends UserRegistrationDto {

    private String confirmationToken;
    private Date createdDate;
    private Boolean status;

    public UserRegistrationRequestDto(@Email(message = "Please provide a valid email address") @Pattern(regexp = "^[a-zA-Z0-9.\\-_]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", message = "Please provide a valid email address") String email, @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[.?!]).{12,}", message = "is not a valid password. Your password must be at least 12 characters long and include at least one lowercase letter, one uppercase letter, one digit, and one special character (., ?, !).") String password) {
        super(email, password);
        this.confirmationToken = UUID.randomUUID().toString();
        this.createdDate = new Date();
        this.status = false;
    }
}
