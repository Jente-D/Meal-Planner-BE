package be.multimedi.mealplanning.user;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
//TODO: userDatabaseService
    private final UserRepository userRepo;

    @Override
    public User registerNewUser(User user) {
        if(user.getId() != null){
            throw new IllegalArgumentException("Cannot save user with existing id: " + user.getId());
        }
        if(userRepo.existsByEmail(user.getEmail())){
            throw new EntityExistsException("Email taken: " + user.getEmail());
        }
        return userRepo.save(user);
    }
}
