package be.multimedi.mealplanning.mealPlan;

import be.multimedi.mealplanning.meals.Meal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class DailyMealPlan {
    private Long id;
    private List<Meal> meals = new ArrayList<>();
}
