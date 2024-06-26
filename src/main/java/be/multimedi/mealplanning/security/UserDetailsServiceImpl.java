package be.multimedi.mealplanning.security;

import be.multimedi.mealplanning.user.User;
import be.multimedi.mealplanning.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userRepo.findByEmailIgnoreCase(email).orElseThrow(()-> new UsernameNotFoundException(email));
        UserDetails userDetails = new UserDetailsImpl(user);
        return userDetails;
    }
}
