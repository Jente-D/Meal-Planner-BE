package be.multimedi.mealplanning.mealSearch;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/meals")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @GetMapping
    @Transactional
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
}
