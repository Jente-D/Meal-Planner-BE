package be.multimedi.mealplanning.user;

import jakarta.mail.MessagingException;

public interface RegistrationService {
    PotentialUser registerPotentialUser(PotentialUserDto potentialUserDto) throws MessagingException;
    User activateUserAccount(String confirmationToken);
}
