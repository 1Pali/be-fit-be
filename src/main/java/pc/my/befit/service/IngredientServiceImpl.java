package pc.my.befit.service;

import com.googlecode.jmapper.JMapper;
import pc.my.befit.Repository.IngredientRepository;
import pc.my.befit.api.dto.AddIngredientDto;
import pc.my.befit.api.dto.IngredientDto;
import pc.my.befit.api.dto.UpdateIngredientDto;
import pc.my.befit.api.service.IngredientService;
import pc.my.befit.exception.ElementDoesNotExistException;
import pc.my.befit.model.entity.Ingredient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static final String INGREDIENT_EXCEPTION_MESSAGE = "Ingredient with id: %s does not exist.";
    private static final JMapper<IngredientDto, Ingredient> INGREDIENT_DTO_MAPPER = new JMapper<>(IngredientDto.class, Ingredient.class);

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(final IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    @Transactional
    public IngredientDto getById(Long id) {
        return ingredientRepository.findById(id)
                .map(INGREDIENT_DTO_MAPPER::getDestination)
                .orElseThrow(() -> new ElementDoesNotExistException(
                        String.format(INGREDIENT_EXCEPTION_MESSAGE, id)
                ));
    }

    @Override
    @Transactional
    public List<IngredientDto> getList() {
        return ingredientRepository.findAll()
                .stream().map(INGREDIENT_DTO_MAPPER::getDestination)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ingredientRepository.findById(id)
                .orElseThrow(() -> new ElementDoesNotExistException(
                        String.format(INGREDIENT_EXCEPTION_MESSAGE, id)));

        ingredientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public IngredientDto update(Long ingredientId, UpdateIngredientDto dto) {
        Ingredient existingIngredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ElementDoesNotExistException(
                        String.format(INGREDIENT_EXCEPTION_MESSAGE, ingredientId)));

        existingIngredient.setName(dto.getName());
        existingIngredient.setDescription(dto.getDescription());
        existingIngredient.setEnergy(dto.getEnergy());
        existingIngredient.setProtein(dto.getProtein());
        existingIngredient.setCarbohydrate(dto.getCarbohydrate());
        existingIngredient.setFat(dto.getFat());
        existingIngredient.setFiber(dto.getFiber());
        existingIngredient.setPrice(dto.getPrice());
        return INGREDIENT_DTO_MAPPER.getDestination(ingredientRepository.save(existingIngredient));
    }

    @Override
    @Transactional
    public IngredientDto create(AddIngredientDto dto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(dto.getName());
        ingredient.setDescription(dto.getDescription());
        ingredient.setEnergy(dto.getEnergy());
        ingredient.setProtein(dto.getProtein());
        ingredient.setCarbohydrate(dto.getCarbohydrate());
        ingredient.setFat(dto.getFat());
        ingredient.setFiber(dto.getFiber());
        ingredient.setPrice(dto.getPrice());
        return INGREDIENT_DTO_MAPPER.getDestination(ingredientRepository.save(ingredient));
    }
}
