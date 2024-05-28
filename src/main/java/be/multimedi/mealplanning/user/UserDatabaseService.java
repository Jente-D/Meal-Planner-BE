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
        if(userRepo.existsByEmail(userRegistrationDto.getEmail())){
            throw new EntityExistsException("Email taken: " + userRegistrationDto.getEmail());
        }
        User user = userRegistrationDto.convertToEntity(userRegistrationDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
}
