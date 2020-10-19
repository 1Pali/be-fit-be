package com.pepa.befit.be_fit_be.api.service;

import com.pepa.befit.be_fit_be.api.dto.AddRecipeDto;
import com.pepa.befit.be_fit_be.api.dto.RecipeDto;
import com.pepa.befit.be_fit_be.api.dto.UpdateRecipeDto;
import com.pepa.befit.be_fit_be.model.entity.Recipe;

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
