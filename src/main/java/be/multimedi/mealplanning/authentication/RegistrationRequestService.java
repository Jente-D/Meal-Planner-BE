package be.multimedi.mealplanning.authentication;

public interface RegistrationRequestService {
    RegistrationRequest registerNewUserRequest (UserRegistrationDto userRegistrationDto);
    User confirmRegistration (String confirmationToken);
}
