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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weekly_meal_plan_id", insertable = false, updatable = false)
    private WeeklyMealPlan weeklyMealPlan;
}
