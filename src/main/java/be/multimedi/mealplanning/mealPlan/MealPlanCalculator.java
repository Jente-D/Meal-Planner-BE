package be.multimedi.mealplanning.mealPlan;

import be.multimedi.mealplanning.meals.MealType;

import java.util.*;

public class MealPlanCalculator {
    public MealsADay determineMealsADay(List<MealType> mealTypes) {
        Collections.sort(mealTypes);
        String mealTypesString = mealTypes.toString();

        if (mealTypesString.equals(Arrays.asList(MealType.BREAKFAST, MealType.LUNCH).toString())) {
            return MealsADay.BREAKFAST_LUNCH;
        } else if (mealTypesString.equals(Arrays.asList(MealType.BREAKFAST, MealType.DINNER).toString())) {
            return MealsADay.BREAKFAST_DINNER;
        } else if (mealTypesString.equals(Arrays.asList(MealType.LUNCH, MealType.DINNER).toString())) {
            return MealsADay.TWO_MEALS;
        } else if (mealTypesString.equals(Arrays.asList(MealType.BREAKFAST, MealType.LUNCH, MealType.DINNER).toString())) {
            return MealsADay.THREE_MEALS;
        } else if (mealTypesString.equals(Arrays.asList(MealType.BREAKFAST, MealType.LUNCH, MealType.DINNER, MealType.SNACK).toString())) {
            return MealsADay.FOUR_MEALS;
        } else {
            return null;
        }
    }

    public Map<MealType, Integer> distributeCalories(int totalCalories, MealsADay mealsADay) {
        Map<MealType, Integer> mealCalories = new HashMap<>();

        switch (mealsADay) {
            case MealsADay.FOUR_MEALS:
                mealCalories.put(MealType.BREAKFAST, totalCalories * 25 / 100);
                mealCalories.put(MealType.LUNCH, totalCalories * 35 / 100);
                mealCalories.put(MealType.DINNER, totalCalories * 30 / 100);
                mealCalories.put(MealType.SNACK, totalCalories * 10 / 100);
                break;
            case MealsADay.THREE_MEALS:
                mealCalories.put(MealType.BREAKFAST, totalCalories * 30 / 100);
                mealCalories.put(MealType.LUNCH, totalCalories * 35 / 100);
                mealCalories.put(MealType.DINNER, totalCalories * 35 / 100);
                break;
            case MealsADay.TWO_MEALS:
                mealCalories.put(MealType.LUNCH, totalCalories * 55 / 100);
                mealCalories.put(MealType.DINNER, totalCalories * 45 / 100);
                break;
            case MealsADay.BREAKFAST_LUNCH:
                mealCalories.put(MealType.BREAKFAST, totalCalories * 35 / 100);
                mealCalories.put(MealType.LUNCH, totalCalories * 65 / 100);
                break;
            case MealsADay.BREAKFAST_DINNER:
                mealCalories.put(MealType.BREAKFAST, totalCalories * 35 / 100);
                mealCalories.put(MealType.DINNER, totalCalories * 65 / 100);
                break;
        }
        return mealCalories;
    }



}
