package be.multimedi.mealplanning.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PotentialUser {
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
}
