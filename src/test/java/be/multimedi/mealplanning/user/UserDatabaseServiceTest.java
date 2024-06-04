package be.multimedi.mealplanning.user;

import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

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
        Long id = 1L; // example id
        String email = "test@gmail.com";
        String password = "Password123!"; // example password
        String name = "Test User"; // example name
        Date dateOfBirth = new Date(); // example date of birth
        Boolean isVerified = false; // example isVerified

        PotentialUserDto userDto = new PotentialUserDto(id, email, password, name, dateOfBirth, isVerified);
        User user = PotentialUserDto.convertToEntity(userDto);
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
        Long id = 1L; // example id
        String email = "test@gmail.com";
        String password = "Password123!"; // example password
        String name = "Test User"; // example name
        Date dateOfBirth = new Date(); // example date of birth
        Boolean isVerified = false; // example isVerified

        PotentialUserDto user = new PotentialUserDto(id, email, password, name, dateOfBirth, isVerified);

        when(userRepo.existsByEmail(any(String.class))).thenReturn(true);

        //Act & Assert
        assertThrows(EntityExistsException.class, () -> userService.registerNewUser(user));
    }
}