package be.multimedi.mealplanning.authentication;

import lombok.*;

@Getter
@AllArgsConstructor
public class UserRegistrationDto {
    private String email;
    private String password;

    //TODO: Model mapper overwegen te gebruiken Libr of mapst... modelmapper niet compatible met record in pom toevoegen daaarvoor + apparte klasse
    public static User convertToEntity(UserRegistrationDto dto){
        return new User(
                null,
                dto.getEmail(),
                dto.getPassword()
        );
    }
}
