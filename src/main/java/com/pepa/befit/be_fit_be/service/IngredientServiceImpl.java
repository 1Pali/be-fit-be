package com.pepa.befit.be_fit_be.service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import com.googlecode.jmapper.JMapper;
import com.pepa.befit.be_fit_be.api.dto.AddIngredientDto;
import com.pepa.befit.be_fit_be.api.dto.IngredientDto;
import com.pepa.befit.be_fit_be.api.dto.UpdateIngredientDto;
import com.pepa.befit.be_fit_be.api.enumeration.IngredientType;
import com.pepa.befit.be_fit_be.api.service.IngredientService;
import com.pepa.befit.be_fit_be.repository.IngredientRepository;
import com.pepa.befit.be_fit_be.exception.ElementDoesNotExistException;
import com.pepa.befit.be_fit_be.model.entity.Ingredient;
import org.springframework.stereotype.Service;

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
        existingIngredient.setPrice(dto.getPrice());
        existingIngredient.setIngredientType(IngredientType.fromId(dto.getIdIngredientType()).toCatIngredientType());
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
        ingredient.setIngredientType(IngredientType.fromId(dto.getIdIngredientType()).toCatIngredientType());
        return INGREDIENT_DTO_MAPPER.getDestination(ingredientRepository.save(ingredient));
    }
}
