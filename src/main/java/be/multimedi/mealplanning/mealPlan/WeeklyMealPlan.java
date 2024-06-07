package be.multimedi.mealplanning.mealPlan;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.List;

@Setter
@Entity
public class WeeklyMealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int weekNumber;
    @OneToMany(mappedBy = "weeklyMealPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meal> meals;
    private int totalCalories;
    private int mealsPerDay;
    private int servingsPerMeal;

}
