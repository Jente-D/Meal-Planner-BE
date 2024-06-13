package be.multimedi.mealplanning.meals;

import java.util.List;

public interface MealService {
    List<Meal> fetchAllMeals();
    List<Meal> findMealByCaloriesBetween(int minCalories, int maxCalories);
    List<Meal> findMealByMealType(String mealtype);
}
