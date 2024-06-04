package be.multimedi.mealplanning.user;

import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDatabaseServiceTest {
    @Mock
    private UserRepository userRepo;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserDatabaseService userService;

    @Test
    void testRegisterNewUserNonexistentEmailShouldSucceed() {
        //Arrange
        UserRegistrationDto userDto = new UserRegistrationDto(
                "test@gmail.com",
                "password"
                );
        User user = UserRegistrationDto.convertToEntity(userDto);
        user.setPassword("encodedpassword");

        when(passwordEncoder.encode(any())).thenAnswer(i -> "encoded" + i.getArguments()[0]);
        when(userRepo.existsByEmail(any(String.class))).thenReturn(false);
        when(userRepo.save(any(User.class))).thenReturn(user);
        //Act
        User registeredUser = userService.registerNewUser(userDto);
        //Assert
        assertEquals("test@gmail.com", registeredUser.getEmail());
        assertEquals("encodedpassword", registeredUser.getPassword());
    }

    @Test
    void testRegisterNewUserExistentEmailShouldThrowException() {
        //Arrange
        UserRegistrationDto user = new UserRegistrationDto(
                "test@gmail.com",
                "password"
                );

        when(userRepo.existsByEmail(any(String.class))).thenReturn(true);

        //Act & Assert
        assertThrows(EntityExistsException.class, () -> userService.registerNewUser(user));
    }
}