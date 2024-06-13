package be.multimedi.mealplanning.meals;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepo;
    @Override
    public List<Meal> fetchAllMeals() {
        List<Meal> meals = mealRepo.findAll();
        if(meals.isEmpty()){
            throw new IllegalArgumentException("Meal library is empty.");
        } else {
            return meals;
        }
    }

   @Override
   public List<Meal> findMealByCaloriesBetween(int minCalories, int maxCalories){
        List<Meal> fittingMeals = mealRepo.findMealsByCaloriesBetween(minCalories, maxCalories);
        if(fittingMeals.isEmpty()){
            throw new IllegalArgumentException("No meals found between the given calories.");
        }else {
            return fittingMeals;
        }
   }

    @Override
    public List<Meal> findMealByMealType(String mealType) {
        List<Meal> filteredMeals = mealRepo.findMealsByMealType(MealType.valueOf(mealType.toUpperCase()));
        if(filteredMeals.isEmpty()){
            throw new IllegalArgumentException("No meals found with that type");
        }else{
            return filteredMeals;
        }
    }

}
