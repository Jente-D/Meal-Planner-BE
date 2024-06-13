package be.multimedi.mealplanning.mealPlan;

import be.multimedi.mealplanning.mealSearch.Meal;
import be.multimedi.mealplanning.mealSearch.MealRepository;
import be.multimedi.mealplanning.mealSearch.MealType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class WeeklyMealPlanServiceImplTest  {
    @Mock
    private MealRepository repo;



    @Test
    void myTest(){
        WeeklyMealPlanServiceImpl service = new WeeklyMealPlanServiceImpl(repo);

        List<MealType> dayPlan = new ArrayList<>();
        dayPlan.add( MealType.BREAKFAST);
        dayPlan.add(MealType.LUNCH);
        dayPlan.add(MealType.DINNER);

        when(repo.findRandomMealByMealTypeAndCaloriesLessThanEqual(any(),any())).thenReturn(new Meal(1L,"test",MealType.BREAKFAST, 500));

        service.createWeeklyMealPlan(16100, dayPlan);

    }



}