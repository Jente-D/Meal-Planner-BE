package be.multimedi.mealplanning.mealPlan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeeklyMealPlanServiceImpl implements WeeklyMealPlanService {
    private final DayPlan dayPlan = new DayPlan();
    private final MealRepository mealRepository;

//    TODO: bepaal de dag restricties voor een dayPlan: mealTypes en prefferedMealType
//    TODO: bepaal de range van Calories per maaltijd van het dayPlan
//    TODO: selecteer random maaltijden voor die maaltijdTypen op basis van type maaltijd en Callorie range
//    TODO: ga voor alle 7 de dagen op basis van de restrictied de maaltijden op halen en zorg dat er geen dubbelen in zitten
//Counter tussen zetten tussen elke maaltijd > lunch, ontbijt, diner


    @Override
    public WeeklyMealPlan createWeeklyMealPlan(int totalCalories, int mealsPerDay, List<MealType> preferredMealTypes) {
        int dailyCalories = totalCalories/7;
        WeeklyMealPlan weeklyMealPlan = new WeeklyMealPlan();
        List<Meal> usedMeals = new ArrayList<>();

        var plan = dayPlan.initializeMealTypesForADay(mealsPerDay, preferredMealTypes);

        for (int i = 0; i < 7; i++) {

            for (MealType mealType : plan ) {
                int mealCalories = (int) (dailyCalories * determineMealTypeCaloriePercentage().get(mealType));
                Meal meal;
                do {
                    meal = mealRepository.findRandomMealByMealTypeAndCaloriesLessThanEqual(mealType, mealCalories);
                } while (usedMeals.contains(meal));
                usedMeals.add(meal);
            }
        }

        weeklyMealPlan.setMeals(usedMeals);
        return weeklyMealPlan;
    }

    private Map<MealType, Double> determineMealTypeCaloriePercentage() {
        return Map.of(
                MealType.BREAKFAST, 0.25,
                MealType.LUNCH, 0.35,
                MealType.DINNER, 0.35,
                MealType.SNACK, 0.05
        );
    }

}
