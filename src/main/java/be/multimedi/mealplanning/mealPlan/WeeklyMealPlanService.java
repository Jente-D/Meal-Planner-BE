package be.multimedi.mealplanning.mealPlan;

import java.util.List;

public interface WeeklyMealPlanService {
    WeeklyMealPlan createWeeklyMealPlan(int totalCalories, int mealsPerDay, List<MealType> preferredMealTypes);
}
