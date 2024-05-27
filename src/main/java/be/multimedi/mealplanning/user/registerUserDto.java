package be.multimedi.mealplanning.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class registerUserDto {
    private String username;
    private String email;
    private String password;
}
