package be.multimedi.mealplanning.user;

public interface RegistrationRequestService {
    RegistrationRequest registerNewUserRequest (UserRegistrationDto userRegistrationDto);
    User confirmRegistration (String confirmationToken);
}
