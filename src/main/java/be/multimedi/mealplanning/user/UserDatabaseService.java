package be.multimedi.mealplanning.user;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDatabaseService implements UserService {
    private final UserRepository userRepo;

    @Override
    public User registerNewUser(UserRegistrationDto UserRegistrationDto) {
        if(userRepo.existsByEmail(UserRegistrationDto.getEmail())){
            throw new EntityExistsException("Email taken: " + UserRegistrationDto.getEmail());
        }
        User user = UserRegistrationDto.convertToEntity(UserRegistrationDto);
        return userRepo.save(user);
    }
}
