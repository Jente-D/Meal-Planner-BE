package be.multimedi.mealplanning.authentication;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@Tag("integrationTest")
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepo;

    @Test
    void existsByEmail() {
        //arrange
        User user = new User();
        user.setEmail("test@gmail.com");
        //Act
        userRepo.save(user);
        //Assert
        assertTrue(userRepo.existsByEmail("test@gmail.com"));
        assertFalse(userRepo.existsByEmail("noemail@gmail.com"));
    }
}