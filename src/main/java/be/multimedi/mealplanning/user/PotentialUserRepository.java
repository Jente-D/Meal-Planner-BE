package be.multimedi.mealplanning.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PotentialUserRepository extends JpaRepository<PotentialUser, Long> {
    Optional <PotentialUser> findByConfirmationToken(String confirmationToken);
    boolean existsByEmailIgnoreCase(String email);
}
