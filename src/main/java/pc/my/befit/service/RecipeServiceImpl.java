package pc.my.befit.service;

import com.google.common.collect.ImmutableList;
import com.googlecode.jmapper.JMapper;
import pc.my.befit.Repository.RecipeRepository;
import pc.my.befit.api.dto.*;
import pc.my.befit.api.service.RecipeService;
import pc.my.befit.exception.ElementDoesNotExistException;
import pc.my.befit.model.entity.Ingredient;
import pc.my.befit.model.entity.Recipe;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static final String RECIPE_EXCEPTION_MESSAGE = "Recipe with id: %s does not exist.";
    private static final JMapper<RecipeDto, Recipe> RECIPE_DTO_MAPPER = new JMapper<>(RecipeDto.class, Recipe.class);

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(final RecipeRepository recipeRepository) {

        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public RecipeDto getById(Long id) {
        return recipeRepository.findById(id)
                .map(RECIPE_DTO_MAPPER::getDestination)
                .orElseThrow(() -> new ElementDoesNotExistException(
                        String.format(RECIPE_EXCEPTION_MESSAGE, id)
                ));
    }

    @Override
    @Transactional
    public List<RecipeDto> getList() {
        return recipeRepository.findAll()
                .stream().map(RECIPE_DTO_MAPPER::getDestination)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        recipeRepository.findById(id)
                .orElseThrow(() -> new ElementDoesNotExistException(
                        String.format(RECIPE_EXCEPTION_MESSAGE, id)));

        recipeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public RecipeDto update(Long recipeId, UpdateRecipeDto dto) {
        Recipe existingExisting = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ElementDoesNotExistException(
                        String.format(RECIPE_EXCEPTION_MESSAGE, recipeId)));

        existingExisting.setName(dto.getName());
//        existingIngredient.setDescription(dto.getDescription());

        return RECIPE_DTO_MAPPER.getDestination(recipeRepository.save(existingExisting));
    }

    @Override
    @Transactional
    public RecipeDto create(AddRecipeDto dto) {
        Recipe recipe = new Recipe();
        recipe.setName(dto.getName());
//        recipe.setDescription(dto.getDescription());

        return RECIPE_DTO_MAPPER.getDestination(recipeRepository.save(recipe));
    }
}
