package be.multimedi.mealplanning.user;

import be.multimedi.mealplanning.messaging.EmailSenderService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationDatabaseService implements RegistrationService {
    private final PotentialUserRepository potentialUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;

    @Override
    public PotentialUser registerPotentialUser(PotentialUserDto potentialUserDto) {
        if(potentialUserRepository.existsByEmail(potentialUserDto.getEmail())){
            throw new EntityExistsException("Email taken: " + potentialUserDto.getEmail());
        }
        PotentialUser potentialUser = new PotentialUser();
        potentialUser.setEmail(potentialUserDto.getEmail());
        potentialUser.setPassword(passwordEncoder.encode(potentialUserDto.getPassword()));
        potentialUser.setCreatedDate(new Date());
        potentialUser.setConfirmationToken(UUID.randomUUID().toString());
        potentialUser = potentialUserRepository.save(potentialUser);
        emailSenderService.sendConfirmationEmail(potentialUser);
        return potentialUser;
    }


    @Override
    public User activateUserAccount(String confirmationToken) {
        PotentialUser potentialUser = potentialUserRepository.findByConfirmationToken(confirmationToken)
                .orElseThrow(() -> new EntityNotFoundException("No registration request found with confirmation token: " + confirmationToken));
        potentialUser.setStatus(true);
        User user = new User();
        user.setEmail(potentialUser.getEmail());
        user.setPassword(potentialUser.getPassword());
        userRepository.save(user);
        return user;
    }
}
