package be.multimedi.mealplanning.mealSearch;

import java.util.List;

public interface MealService {
    List<Meal> fetchAllMeals();
    List<Meal> findMealByCaloriesBetween(int minCalories, int maxCalories);
}
