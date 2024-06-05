package be.multimedi.mealplanning.mealPlan;

import jakarta.persistence.*;

@Entity
@Table(name = "allergies")
public class Allergy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
