package be.multimedi.mealplanning.user;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDatabaseService implements UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Deprecated
    @Override
    public User registerNewUser(PotentialUserDto potentialUserDto) {
        if(userRepo.existsByEmailIgnoreCase(potentialUserDto.getEmail())){
            throw new EntityExistsException("Email taken: " + potentialUserDto.getEmail());
        }
        User user = potentialUserDto.convertToEntity(potentialUserDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
}
