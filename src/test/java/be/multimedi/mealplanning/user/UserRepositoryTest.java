package be.multimedi.mealplanning.user;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Tag("integrationTest")
@RunWith(MockitoJUnitRunner.class)
class UserRepositoryTest {
    @Mock
    private UserRepository userRepo;

    @Test
    void existsByEmail() {
        // Arrange
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("123456789Aa?");

        when(userRepo.existsByEmail("test@gmail.com")).thenReturn(true);
        when(userRepo.existsByEmail("noemail@gmail.com")).thenReturn(false);

        // Assert
        assertTrue(userRepo.existsByEmail("test@gmail.com"));
        assertFalse(userRepo.existsByEmail("noemail@gmail.com"));
    }

    @Test
    void findByEmailIgnoreCase() {
        // Arrange
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("123456789Aa?");

        when(userRepo.findByEmailIgnoreCase("test@gmail.com")).thenReturn(Optional.of(user));
        when(userRepo.findByEmailIgnoreCase("noemail@gmail.com")).thenReturn(Optional.empty());

        // Assert
        assertEquals(user, userRepo.findByEmailIgnoreCase("test@gmail.com").orElse(null));
        assertFalse(userRepo.findByEmailIgnoreCase("noemail@gmail.com").isPresent());
    }
}