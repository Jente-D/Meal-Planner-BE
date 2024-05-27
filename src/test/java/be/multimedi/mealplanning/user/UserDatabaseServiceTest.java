package be.multimedi.mealplanning.user;

import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDatabaseServiceTest {
    @Mock
    private UserRepository userRepo;
    @InjectMocks
    private UserDatabaseService userService;

    @Test
    void testRegisterNewUserNonexistentEmailShouldSucceed() {
        //Arrange
        UserRegistrationDto userDto = UserRegistrationDto.builder()
                .username("test")
                .email("test@gmail.com")
                .password("password")
                .build();
        User user = UserRegistrationDto.convertToEntity(userDto);

        when(userRepo.existsByEmail(any(String.class))).thenReturn(false);
        when(userRepo.save(any(User.class))).thenReturn(user);
        //Act
        User registeredUser = userService.registerNewUser(userDto);
        //Assert
        assertEquals("test@gmail.com", registeredUser.getEmail());
    }

    @Test
    void testRegisterNewUserExistentEmailShouldThrowException() {
        //Arrange
        UserRegistrationDto user = UserRegistrationDto.builder()
                .username("test")
                .email("test@gmail.com")
                .password("password")
                .build();

        when(userRepo.existsByEmail(any(String.class))).thenReturn(true);

        //Act & Assert
        assertThrows(EntityExistsException.class, () -> userService.registerNewUser(user));
    }

    @Test
    void testRegisterNewUserEmptyEmailShouldThrowException() {
        //Arrange
        UserRegistrationDto user = UserRegistrationDto.builder()
                .username("test")
                .email("")
                .password("password")
                .build();

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userService.registerNewUser(user));
    }
}