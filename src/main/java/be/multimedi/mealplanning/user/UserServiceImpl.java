package be.multimedi.mealplanning.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    @Override
    public User registerNewUser(User user) {
        if(user.getId() != null){
            throw new IllegalArgumentException("Cannot save user with existing id: " + user.getId());
        }
        return userRepo.save(user);
    }
}
