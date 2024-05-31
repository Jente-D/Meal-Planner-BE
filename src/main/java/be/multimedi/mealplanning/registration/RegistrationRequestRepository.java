package be.multimedi.mealplanning.registration;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {
    RegistrationRequest findByConfirmationToken(String confirmationToken);
}
