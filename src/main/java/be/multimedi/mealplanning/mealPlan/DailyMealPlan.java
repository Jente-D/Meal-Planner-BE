package be.multimedi.mealplanning.mealPlan;

import be.multimedi.mealplanning.meals.Meal;
import jakarta.persistence.*;

import java.util.List;


public class DailyMealPlan {
    private Long id;
    private List<Meal> meals;
}
