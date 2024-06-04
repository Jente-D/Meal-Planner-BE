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
public class RegistrationRequestDatabaseService implements RegistrationRequestService {
    private final RegistrationRequestRepository registrationRequestRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;

    @Override
    public RegistrationRequest registerNewUserRequest(UserRegistrationDto userRegistrationDto) {
        if(registrationRequestRepository.existsByEmail(userRegistrationDto.getEmail())){
            throw new EntityExistsException("Email taken: " + userRegistrationDto.getEmail());
        }
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmail(userRegistrationDto.getEmail());
        registrationRequest.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        registrationRequest.setCreatedDate(new Date());
        registrationRequest.setConfirmationToken(UUID.randomUUID().toString());
        registrationRequest = registrationRequestRepository.save(registrationRequest);
        emailSenderService.sendConfirmationEmail(registrationRequest);
        return registrationRequest;
    }


    @Override
    public User confirmRegistration(String confirmationToken) {
        RegistrationRequest registrationRequest = registrationRequestRepository.findByConfirmationToken(confirmationToken)
                .orElseThrow(() -> new EntityNotFoundException("No registration request found with confirmation token: " + confirmationToken));
        registrationRequest.setStatus(true);
        User user = new User();
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(registrationRequest.getPassword());
        userRepository.save(user);
        return user;
    }
}
