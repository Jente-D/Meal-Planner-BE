package be.multimedi.mealplanning.mealPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query("SELECT m FROM Meal m WHERE m.calories <= :calories")
    List<Meal> findMealsByCaloriesLessThanEqual(@Param("calories") int calories);

    List<Meal> findMealsByMealType(MealType mealType);
}
