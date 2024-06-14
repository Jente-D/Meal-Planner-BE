package be.multimedi.mealplanning.mealPlan;

import be.multimedi.mealplanning.meals.MealType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/mealplan")
@RequiredArgsConstructor
public class MealPlanController {
    private final DailyMealPlanService dailyMealPlanService;

    @PostMapping
    public ResponseEntity<DailyMealPlan> createDailyMealPlan(@RequestParam List<MealType> mealTypes, @RequestParam int totalCalories) {
        return dailyMealPlanService.createDailyMealPlan(mealTypes, totalCalories)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
