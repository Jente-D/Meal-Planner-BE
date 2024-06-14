package be.multimedi.mealplanning.mealPlan;

import be.multimedi.mealplanning.meals.Meal;
import be.multimedi.mealplanning.meals.MealRepository;
import be.multimedi.mealplanning.meals.MealType;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DailyMealPlanServiceImpl implements DailyMealPlanService {
    private final MealPlanCalculator mealPlanCalculator;
    private final MealRepository mealRepo;

    @Override
    public Optional<DailyMealPlan> createDailyMealPlan(List<MealType> mealTypes, int totalCalories) {
        MealsADay meals = mealPlanCalculator.determineMealsADay(mealTypes);
        Map<MealType, Integer> MealTypeWithTotCal= mealPlanCalculator.distributeCalories(totalCalories, meals);

        DailyMealPlan dailyMealPlan = new DailyMealPlan();
        for (MealType mealType : mealTypes) {
            int targetCalories = MealTypeWithTotCal.get(mealType);
            List<Meal> potentialMeals = mealRepo.findMealsByMealTypeAndCaloriesBetween(mealType, targetCalories - 50, targetCalories + 50);
            if (!potentialMeals.isEmpty()) {
                Meal meal = potentialMeals.get(new Random().nextInt(potentialMeals.size()));
                dailyMealPlan.getMeals().add(meal);
            }
        }

        return Optional.of(dailyMealPlan);
    }
}
