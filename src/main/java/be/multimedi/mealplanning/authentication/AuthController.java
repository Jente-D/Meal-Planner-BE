package be.multimedi.mealplanning.authentication;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authManager;
    private final RegistrationRequestService registrationRequestService;

    @PostMapping("/register")
    public ResponseEntity<String> handleRegisterNewUserRequest (@Valid @RequestBody UserRegistrationDto userDto, BindingResult br){
        try{
        if(br.hasErrors()){
            String errorMsg;
            if (br.hasFieldErrors("email")) {
                errorMsg = br.getFieldError("email").getDefaultMessage();
            } else if (br.hasFieldErrors("password")) {
                errorMsg = br.getFieldError("password").getDefaultMessage();
            } else {
                errorMsg = "Validation error";
            }
            throw new IllegalArgumentException(errorMsg);
        }}
        catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
            return ResponseEntity.badRequest().build();
        }
        registrationRequestService.registerNewUserRequest(userDto);
        return ResponseEntity.ok("A confimation email has been sent to " + userDto.getEmail());
    }

    @PostMapping("/login")
    public ResponseEntity<String> handleLogin (@Valid @RequestBody UserLoginDto loginDto, BindingResult br){
        if(br.hasErrors()){
            throw new IllegalArgumentException("Invallid User sign in DTO");
        }
//        Spring security does all te work for us
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        if (!auth.isAuthenticated()){
            throw new BadCredentialsException("invalid credentials");
        }
        return ResponseEntity.ok("You are logged in as " + loginDto.getEmail());
    }
}
