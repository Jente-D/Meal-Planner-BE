package be.multimedi.mealplanning.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRegistrationDto {
    @NotBlank
    @Email(message="Please provide a valid email address")
    @Pattern(regexp="^[a-zA-Z0-9.\\-_]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", message="Please provide a valid email address")
    private String email;
    @NotBlank
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[.?!]).{12,}", message = "is not a valid password. Your password must be at least 12 characters long and include at least one lowercase letter, one uppercase letter, one digit, and one special character (., ?, !).")
    private String password;

    //TODO: Model mapper overwegen te gebruiken Libr of mapst... modelmapper niet compatible met record in pom toevoegen daaarvoor + apparte klasse
    public static User convertToEntity(UserRegistrationDto dto){
        return User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}
