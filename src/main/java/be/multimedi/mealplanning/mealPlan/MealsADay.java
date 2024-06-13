package be.multimedi.mealplanning.mealPlan;

import be.multimedi.mealplanning.meals.MealType;

import java.util.Arrays;
import java.util.List;

public enum MealsADay {
    FOUR_MEALS(Arrays.asList(MealType.BREAKFAST, MealType.LUNCH, MealType.DINNER, MealType.SNACK)),
    THREE_MEALS(Arrays.asList(MealType.BREAKFAST, MealType.LUNCH, MealType.DINNER)),
    TWO_MEALS(Arrays.asList(MealType.LUNCH, MealType.DINNER)),
    BREAKFAST_LUNCH(Arrays.asList(MealType.BREAKFAST, MealType.LUNCH)),
    BREAKFAST_DINNER(Arrays.asList(MealType.BREAKFAST, MealType.DINNER));

    private final List<MealType> mealTypes;

    MealsADay(List<MealType> mealTypes) {
        this.mealTypes = mealTypes;
    }

    public List<MealType> getMealTypes() {
        return mealTypes;
    }
}
