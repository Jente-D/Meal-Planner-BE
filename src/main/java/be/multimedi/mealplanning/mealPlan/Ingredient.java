package be.multimedi.mealplanning.mealPlan;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "ingredient_allergies",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id"))
    private List<Allergy> allergies;
    @ManyToMany(mappedBy = "ingredients")
    private List<Meal> meals;
}
