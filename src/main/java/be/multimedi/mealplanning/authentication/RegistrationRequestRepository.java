package be.multimedi.mealplanning.authentication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {
    RegistrationRequest findByConfirmationToken(String confirmationToken);
}
