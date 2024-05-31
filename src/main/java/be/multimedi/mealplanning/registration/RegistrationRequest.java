package be.multimedi.mealplanning.registration;

import be.multimedi.mealplanning.authentication.UserRegistrationDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RegistrationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean status;

    public RegistrationRequest(UserRegistrationDto userRegistrationDto) {
        this.email = userRegistrationDto.getEmail();
        this.password = userRegistrationDto.getPassword();
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    @PrePersist
    protected void setCreationDate() {
        createdDate = new Date();
    }
}
