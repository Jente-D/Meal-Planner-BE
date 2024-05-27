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

    public static User convertToEntity(registerUserDto dto){
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}
