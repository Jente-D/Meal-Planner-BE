package be.multimedi.mealplanning.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegistrationRequestService {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private UserRepository userRepository;

    public RegistrationRequest createRegistrationRequest(UserRegistrationRequestDto userDto) {
        RegistrationRequest registrationRequest = new RegistrationRequest(userDto);

        // Save the registration request to the database
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
}

