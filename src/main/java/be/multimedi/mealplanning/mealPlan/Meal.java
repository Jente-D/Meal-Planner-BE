package be.multimedi.mealplanning.mealPlan;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private MealType mealType;
    private int calories;
    @Enumerated(EnumType.STRING)
    private AuthorType authorType;
    @ManyToMany
    private List<Ingredient> ingredients;
}
