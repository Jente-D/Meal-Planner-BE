package be.multimedi.mealplanning.user;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column (nullable = false, unique = true)
    private String email;
    @Column (nullable = false)
    private String password;
}
