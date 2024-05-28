package be.multimedi.mealplanning.user;

import jakarta.persistence.EntityExistsException;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDatabaseService implements UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUser(UserRegistrationDto userRegistrationDto) {
//        TODO: extra uitleg rond validatie gebruik makend van je annotaties
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserRegistrationDto>> violations = validator.validate(userRegistrationDto);
        if (!violations.isEmpty()) {
            String errorMessage = violations.iterator().next().getMessage();
            throw new IllegalArgumentException(errorMessage);
        }
        if(userRepo.existsByEmail(userRegistrationDto.getEmail())){
            throw new EntityExistsException("Email taken: " + userRegistrationDto.getEmail());
        }
//        TODO: zet je u pasworodencodere al op de dto of pas op de user? (ik zou denken zo snel mogelijk)
        userRegistrationDto.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        User user = userRegistrationDto.convertToEntity(userRegistrationDto);
        return userRepo.save(user);
    }
}
