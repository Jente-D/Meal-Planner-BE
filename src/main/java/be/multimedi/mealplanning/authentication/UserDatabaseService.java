package be.multimedi.mealplanning.authentication;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
