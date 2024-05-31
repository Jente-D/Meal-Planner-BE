package be.multimedi.mealplanning.registration;

import be.multimedi.mealplanning.authentication.*;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationRequestService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private UserRepository userRepository;

    public RegistrationRequest createRegistrationRequest(UserRegistrationRequestDto userDto) {
        if (registrationRequestRepository.existsByEmail(userDto.getEmail())) {
            throw new EntityExistsException("Email taken: " + userDto.getEmail());
        }
        RegistrationRequest registrationRequest = new RegistrationRequest(userDto);
        registrationRequest.setPassword(passwordEncoder.encode(userDto.getPassword()));
        registrationRequestRepository.save(registrationRequest);
        return registrationRequest;
    }

    public void sendConfirmationEmail(RegistrationRequest registrationRequest) {
        emailSenderService.sendConfirmationEmail(registrationRequest);
    }

    public boolean confirmRegistration(String token) {
        RegistrationRequest registrationRequest = registrationRequestRepository.findByConfirmationToken(token);

        if (registrationRequest != null && !registrationRequest.isStatus()) {
            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setPassword(registrationRequest.getPassword());

            userRepository.save(user);

            registrationRequest.setStatus(true);

            registrationRequestRepository.delete(registrationRequest);

            return true;
        } else {
            return false;
        }
    }
    public boolean existsByEmail(String email) {
        return registrationRequestRepository.existsByEmail(email);
    }
}

