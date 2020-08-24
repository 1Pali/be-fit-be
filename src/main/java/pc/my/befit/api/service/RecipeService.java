package pc.my.befit.api.service;

import pc.my.befit.api.dto.AddRecipeDto;
import pc.my.befit.api.dto.RecipeDto;
import pc.my.befit.api.dto.UpdateRecipeDto;
import pc.my.befit.model.entity.Recipe;

import java.util.List;

public interface RecipeService {
    /**
     * Get recipe by id
     *
     * @param id Long of recipe
     * @return recipe with given id
     */
    RecipeDto getById(final Long id);

    /**
     * Get list of all recipe
     *
     * @return list of all recipe
     */
    List<RecipeDto> getList();

    /**
     * delete recipe by id
     *
     * @param id Long of recipe
     */
    void delete(final Long id);

    /**
     * Update {@link Recipe} with given values from {@link UpdateRecipeDto}
     *
     * @param {@link UpdateIngredientDto}
     * @return updated ingredient
     */
    RecipeDto update(final Long recipeId, final UpdateRecipeDto dto);

    /**
     * Create {@link Recipe} with given values from {@link AddRecipeDto}
     *
     * @param {@link UpdateIngredientDto}
     * @return created recipe
     */
    RecipeDto create(final AddRecipeDto dto);
}
