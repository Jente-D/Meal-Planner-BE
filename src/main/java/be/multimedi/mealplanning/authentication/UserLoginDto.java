package be.multimedi.mealplanning.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter

@AllArgsConstructor
public class UserLoginDto {
//    TODO: weer dezelfde validator? of toch iets anders?
//    TODO: wat is dan het verschil met de registeredDTO of is dit voor toekomst project groei al goed zodat DTO maar in 1 senario gebruikt word

    private String email;
    private String password;

    private static User convertToEntity(UserLoginDto dto){
        return User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}
