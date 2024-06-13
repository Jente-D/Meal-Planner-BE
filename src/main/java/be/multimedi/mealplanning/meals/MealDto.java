package be.multimedi.mealplanning.meals;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MealDto {
    private Long id;
    private String title;
    private MealType mealType;
    private int calories;
    private String imgUrl;

    public static MealDto convertToDto(Meal meal) {
        return MealDto.builder()
                .id(meal.getId())
                .title(meal.getTitle())
                .mealType(meal.getMealType())
                .calories(meal.getCalories())
                .imgUrl(meal.getImgUrl())
                .build();
    }
}


