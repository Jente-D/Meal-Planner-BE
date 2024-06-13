package be.multimedi.mealplanning.meals;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/meals")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @GetMapping
    @Transactional
    @PreAuthorize(("isAuthenticated()"))
    public List<MealDto> getAllMeals() {
        return mealService.fetchAllMeals().stream()
                .map(MealDto::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/filter")
    @Transactional
    public List<MealDto> getMealsByCaloriesBetween(@RequestParam("minCalories") int minCalories, @RequestParam("maxCalories") int maxCalories) {
        return mealService.findMealByCaloriesBetween(minCalories, maxCalories).stream()
                .map(MealDto::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/filter-type")
    @Transactional
    public List<MealDto> getMealsByMealType(@RequestParam("mealType") String mealType) {
        return mealService.findMealByMealType(mealType).stream()
                .map(MealDto::convertToDto)
                .collect(Collectors.toList());
    }
}
