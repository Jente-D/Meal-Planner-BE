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
    private String username;
    @NotBlank
    @Email(message="Please provide a valid email address")
    @Pattern(regexp="^[a-zA-Z0-9.\\-_]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", message="Please provide a valid email address")
    private String email;
    @NotBlank
    private String password;

    public static User convertToEntity(UserRegistrationDto dto){
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}
