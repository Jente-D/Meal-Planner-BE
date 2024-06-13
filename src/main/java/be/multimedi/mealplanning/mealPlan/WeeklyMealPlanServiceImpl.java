//package be.multimedi.mealplanning.mealPlan;
//
//import be.multimedi.mealplanning.mealSearch.Meal;
//import be.multimedi.mealplanning.mealSearch.MealRepository;
//import be.multimedi.mealplanning.mealSearch.MealType;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class WeeklyMealPlanServiceImpl implements WeeklyMealPlanService {
//    private final int DAYS_A_WEEK = 7;
//    private final MealRepository mealRepository;
//
//    @Override
//    public WeeklyMealPlan createWeeklyMealPlan(int totalCalories, List<MealType> preferredMealTypes) {
//        int caloriesADay = totalCalories/DAYS_A_WEEK;
//        int caloriesPerMeal = caloriesADay/preferredMealTypes.size();
////        START: standaard dag bestaat uit 3 hoofdmaaltijden
//
//        Map<MealType, Meal> dayOne = new HashMap<>();
//
//        for (MealType mealType : preferredMealTypes) {
//            int totalCallories = 0;
//
//           Meal meal = mealRepository.findRandomMealByMealTypeAndCaloriesLessThanEqual(mealType, caloriesPerMeal);
//            totalCallories += meal.getCalories();
//            dayOne.put(mealType, meal);
//        }
//
//        for (Map.Entry<MealType, Meal> entry : dayOne.entrySet()) {
//            MealType mealType = entry.getKey();
//            Meal meal = entry.getValue();
//
//            System.out.println("MealType: " + mealType);
//            System.out.println("Meal: " + meal.getTitle());
//            System.out.println("Calories: " + meal.getCalories());
//        }
//
//        return null;
//    }
//
//}
