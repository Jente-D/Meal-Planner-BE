package be.multimedi.mealplanning.user;

import be.multimedi.mealplanning.messaging.EmailSenderService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationDatabaseService implements RegistrationService {
    private final PotentialUserRepository potentialUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;

    @Override
    public PotentialUser registerPotentialUser(PotentialUserDto potentialUserDto) throws MessagingException {
        if(emailIsNotUnique(potentialUserDto.getEmail())){
            throw new IllegalArgumentException("user with that email already exist" +potentialUserDto.getEmail());
        }
        PotentialUser potentialUser = new PotentialUser();
        potentialUser.setEmail(potentialUserDto.getEmail().toLowerCase());
        potentialUser.setPassword(passwordEncoder.encode(potentialUserDto.getPassword()));
        potentialUser.setCreatedDate(LocalDate.now());
        potentialUser.setConfirmationToken(UUID.randomUUID().toString());
        potentialUser = potentialUserRepository.save(potentialUser);
        emailSenderService.sendConfirmationEmail(potentialUser);
        return potentialUser;
    }

    private boolean emailIsNotUnique(String email){
        return potentialUserRepository.existsByEmailIgnoreCase(email) || userRepository.existsByEmailIgnoreCase(email);
    }


    @Override
    public User activateUserAccount(String confirmationToken) {
        PotentialUser potentialUser = potentialUserRepository.findByConfirmationToken(confirmationToken)
                .orElseThrow(() -> new EntityNotFoundException("No registration request found with confirmation token: " + confirmationToken));
        potentialUser.setStatus(true);

        return userRepository.save(createActiveUser(potentialUser));
    }

    private User createActiveUser(PotentialUser potential){
        User user = new User();
        user.setEmail(potential.getEmail());
        user.setPassword(potential.getPassword());
        return user;
    }
}
