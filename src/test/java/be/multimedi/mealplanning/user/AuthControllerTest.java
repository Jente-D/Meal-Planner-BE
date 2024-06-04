package be.multimedi.mealplanning.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integrationTest")
@RunWith(MockitoJUnitRunner.class)
class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .build();
    }

    @Test
    public void givenFaultUserAuthenticationWhenRegistrationRequestIsMadeThenReturn400Nok() throws Exception {
//       GIVEN
        UserRegistrationDto registrationDto = new UserRegistrationDto(
                "invalidEmail",
                "invalidPassword"
                );
//        WHEN & then / ACT & ASSERT
        when(userService.registerNewUser(any(UserRegistrationDto.class)))
                .thenReturn(UserRegistrationDto.convertToEntity(registrationDto));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/register")
                        .content(new ObjectMapper().writeValueAsString(registrationDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void givenFaultUserAuthenticationWhenAuthenticationRequestIsMadeThenReturn400Nok() {

    }

}