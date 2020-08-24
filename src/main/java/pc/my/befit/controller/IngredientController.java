package pc.my.befit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pc.my.befit.api.dto.AddIngredientDto;
import pc.my.befit.api.dto.IngredientDto;
import pc.my.befit.api.dto.UpdateIngredientDto;
import pc.my.befit.model.entity.Ingredient;
import pc.my.befit.api.service.IngredientService;
import org.springframework.http.MediaType;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/api/v1/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(final IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IngredientDto>> getListOfIngredients() {
        return ResponseEntity.ok(ingredientService.getList());
    }

    @GetMapping(value = "/{ingredientid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IngredientDto> getIngredientById(@PathVariable("ingredientid") @NotNull Long ingredientId) {
        return ResponseEntity.ok(ingredientService.getById(ingredientId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IngredientDto> createIngredient(@Valid @RequestBody AddIngredientDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientService.create(dto));
    }

    @PutMapping(value = "/{ingredientid}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IngredientDto> updateIngredient(@PathVariable("ingredientid") @NotNull Long ingredientId,
                                                @RequestBody UpdateIngredientDto dto) {

        return ResponseEntity.accepted().body(ingredientService.update(ingredientId, dto));
    }

    @DeleteMapping(value = "/{ingredientid}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable("ingredientid") @NotNull Long ingredientId) {
        ingredientService.delete(ingredientId);
        return ResponseEntity.noContent().build();
    }

}
