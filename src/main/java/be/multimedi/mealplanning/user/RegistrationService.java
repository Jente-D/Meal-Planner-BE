package be.multimedi.mealplanning.user;

public interface RegistrationService {
    PotentialUser registerPotentialUser(PotentialUserDto potentialUserDto);
    User activateUserAccount(String confirmationToken);
}
