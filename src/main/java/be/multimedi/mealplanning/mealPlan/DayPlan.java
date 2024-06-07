package be.multimedi.mealplanning.mealPlan;

import org.springframework.boot.autoconfigure.freemarker.FreeMarkerTemplateAvailabilityProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class DayPlan {
    private List<MealType> mealTypes;
//    RANDOM weg
//    zelf bulder maken? zonder breackfast,standaard is primaryMeals + 2 snacks, createAdjustedDaiy plan
    private Random random = new Random();

    protected List<MealType> initializeMealTypesForADay(int mealsPerDay, List<MealType> preferredMealTypes) {
        List<MealType> primaryMealTypes = List.of(MealType.BREAKFAST, MealType.LUNCH, MealType.DINNER);

        preferredMealTypes.forEach(mealType -> {
            if (primaryMealTypes.contains(mealType) && !mealTypes.contains(mealType)) {
                mealTypes.add(mealType);
            }
        });

        IntStream.range(mealTypes.size(), Math.min(mealsPerDay, 3)).forEach(i -> {
            MealType mealType;
            do {
                mealType = primaryMealTypes.get(random.nextInt(primaryMealTypes.size()));
            } while (mealTypes.contains(mealType));
            mealTypes.add(mealType);
        });

        IntStream.range(mealTypes.size(), mealsPerDay).forEach(i -> mealTypes.add(MealType.SNACK));
        return mealTypes;
    }
}
