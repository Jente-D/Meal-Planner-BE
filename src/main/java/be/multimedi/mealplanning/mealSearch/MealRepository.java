package be.multimedi.mealplanning.mealSearch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query("SELECT m FROM Meal m WHERE m.calories >= :minCalories AND m.calories <= :maxCalories")
    List<Meal> findMealsByCaloriesBetween(@Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories);

    List<Meal> findMealsByMealType(MealType mealType);

//    Optional<Meal> findRandomMealByMealTypeAndCaloriesLessThanEqual (MealType mealType, int calories);
}
