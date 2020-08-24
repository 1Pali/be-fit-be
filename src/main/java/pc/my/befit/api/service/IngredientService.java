package pc.my.befit.api.service;

import pc.my.befit.api.dto.IngredientDto;
import pc.my.befit.api.dto.UpdateIngredientDto;
import pc.my.befit.api.dto.AddIngredientDto;
import pc.my.befit.model.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    /**
     * Get ingredient by id
     *
     * @param id Long of Ingredient
     * @return ingredient with given id
     */
    IngredientDto getById(final Long id);

    /**
     * Get list of all ingredients
     *
     * @return list of all ingredients
     */
    List<IngredientDto> getList();

    /**
     * delete ingredient by id
     *
     * @param id Long of Ingredient
     */
    void delete(final Long id);

    /**
     * Update {@link Ingredient} with given values from {@link UpdateIngredientDto}
     *
     * @param Id of updated ingredient
     * @param {@link UpdateIngredientDto}
     * @return updated ingredient
     */
    IngredientDto update(final Long ingredientId, final UpdateIngredientDto dto);

    /**
     * Create {@link Ingredient} with given values from {@link AddIngredientDto}
     *
     * @param {@link UpdateIngredientDto}
     * @return created ingredient
     */
    IngredientDto create(final AddIngredientDto dto);
}
