package be.multimedi.mealplanning.user;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@RequiredArgsConstructor
public class VerificationController {
    private final UserService userService;
    private final AuthenticationManager authManager;
    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> handleRegisterNewUserRequest (@Valid @RequestBody PotentialUserDto userDto, BindingResult br) throws MessagingException {
        try {
            if (br.hasErrors()) {
                String errorMsg;
                if (br.hasFieldErrors("email")) {
                    errorMsg = br.getFieldError("email").getDefaultMessage();
                } else if (br.hasFieldErrors("password")) {
                    errorMsg = br.getFieldError("password").getDefaultMessage();
                } else {
                    errorMsg = "Validation error";
                }
                throw new IllegalArgumentException(errorMsg);
            }
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            return ResponseEntity.badRequest().build();
        }
        registrationService.registerPotentialUser(userDto);
        Map<String, String> response = new HashMap<>();
        response.put("message", "A confirmation email has been sent to " + userDto.getEmail());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> handleConfirmRegistration(@RequestParam("token") String confirmationToken) {
        User user = registrationService.activateUserAccount(confirmationToken);
        return ResponseEntity.ok("Account for " + user.getEmail() + " has been activated.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> handleLogin (@Valid @RequestBody UserLoginDto loginDto, BindingResult br){
        if(br.hasErrors()){
            throw new IllegalArgumentException("Invalid User sign in DTO");
        }
//        Spring security does all te work for us
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        if (!auth.isAuthenticated()){
            throw new BadCredentialsException("Invalid credentials");
        }
        return ResponseEntity.ok("You are logged in as " + loginDto.getEmail());
    }
}
