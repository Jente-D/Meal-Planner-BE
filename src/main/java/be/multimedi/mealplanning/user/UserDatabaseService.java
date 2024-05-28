package be.multimedi.mealplanning.user;

import jakarta.persistence.EntityExistsException;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDatabaseService implements UserService {
    private final UserRepository userRepo;

    @Override
    public User registerNewUser(UserRegistrationDto UserRegistrationDto) {
//        TODO: extra uitleg rond validatie gebruik makend van je annotaties
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserRegistrationDto>> violations = validator.validate(UserRegistrationDto);
        if (!violations.isEmpty()) {
            String errorMessage = violations.iterator().next().getMessage();
            throw new IllegalArgumentException(errorMessage);
        }
        if(userRepo.existsByEmail(UserRegistrationDto.getEmail())){
            throw new EntityExistsException("Email taken: " + UserRegistrationDto.getEmail());
        }
        User user = UserRegistrationDto.convertToEntity(UserRegistrationDto);
        return userRepo.save(user);
    }
}
