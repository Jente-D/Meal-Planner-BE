package be.multimedi.mealplanning.user;

public interface UserService {
    //TODO: omvormen userDTO naar user al dan niet in controller of service > consistent zijn
    User registerNewUser(registerUserDto registerUserDto);
}
