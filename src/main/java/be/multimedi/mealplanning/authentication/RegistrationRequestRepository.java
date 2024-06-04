package be.multimedi.mealplanning.authentication;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {
    Optional <RegistrationRequest> findByConfirmationToken(String confirmationToken);
    boolean existsByEmail(String email);
}
