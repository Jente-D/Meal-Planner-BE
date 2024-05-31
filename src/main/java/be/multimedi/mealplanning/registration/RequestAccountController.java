package be.multimedi.mealplanning.registration;

import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/v1/request")
public class RequestAccountController {
    @Autowired
    private RegistrationRequestService registrationRequestService;

    @PostMapping("/register")
    public ResponseEntity<String> handleRegisterNewUser(@Valid @RequestBody UserRegistrationRequestDto userDto, BindingResult br) {
        if (br.hasErrors()) {
            String errorMsg;
            if (br.hasFieldErrors("email")) {
                errorMsg = br.getFieldError("email").getDefaultMessage();
            } else if (br.hasFieldErrors("password")) {
                errorMsg = br.getFieldError("password").getDefaultMessage();
            } else {
                errorMsg = "Validation error";
            }
            return ResponseEntity.badRequest().body(errorMsg);
        }
        if (registrationRequestService.existsByEmail(userDto.getEmail())) {
            throw new EntityExistsException("Email taken: " + userDto.getEmail());
        }
        RegistrationRequest registrationRequest = registrationRequestService.createRegistrationRequest(userDto);
        registrationRequestService.sendConfirmationEmail(registrationRequest);
        return ResponseEntity.ok("Registration request created for " + userDto.getEmail());
    }

    @GetMapping("/request/confirm")
    public ResponseEntity<?> confirmRegistrationRequest(@RequestParam("token") String token) {
        boolean isConfirmed = registrationRequestService.confirmRegistration(token);
        if (isConfirmed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
