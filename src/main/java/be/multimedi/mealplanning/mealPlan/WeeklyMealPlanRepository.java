package be.multimedi.mealplanning.mealPlan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeeklyMealPlanRepository extends JpaRepository<WeeklyMealPlan, Long> {
    List<WeeklyMealPlan> findByWeekNumber(int weekNumber);
    List<WeeklyMealPlan> findByMealsPerDay(int mealsPerDay);

}
