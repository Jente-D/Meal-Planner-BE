package be.multimedi.mealplanning.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> handleRegisterNewUser(@Valid @RequestBody UserRegistrationDto userDto, BindingResult br){
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
        }
        userService.registerNewUser(userDto);
        return ResponseEntity.ok("You can now login as " + userDto.getEmail());
    }
}
