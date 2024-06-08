package be.multimedi.mealplanning.mealSearch;

import java.util.List;

public interface MealService {
    List<Meal> fetchAllMeals();
    List<Meal> findMealByCalories (int minCalories, int maxCalories);
}
