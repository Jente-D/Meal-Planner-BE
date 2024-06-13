package be.multimedi.mealplanning.mealPlan;

import be.multimedi.mealplanning.meals.MealType;

import java.util.List;
import java.util.Optional;

public interface DailyMealPlanService {
    Optional<DailyMealPlan> createDailyMealPlan (List<MealType> mealTypes, int totalCalories);
}
