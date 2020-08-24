package pc.my.befit.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pc.my.befit.api.dto.*;
import pc.my.befit.model.entity.Recipe;
import pc.my.befit.api.service.RecipeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(final RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecipeDto>> getListOfRecipes() {
        return ResponseEntity.ok(recipeService.getList());
    }

    @GetMapping(value = "/{recipeid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeDto> getGroupById(@PathVariable("recipeid") @NotNull Long recipeId) {
        return ResponseEntity.ok(recipeService.getById(recipeId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeDto> createGroup(@Valid @RequestBody AddRecipeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.create(dto));
    }

    @PutMapping(value = "/{recipeid}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeDto> updateGroup(@PathVariable("recipeid") @NotNull Long recipeId,
                                                     @RequestBody UpdateRecipeDto dto) {

        return ResponseEntity.accepted().body(recipeService.update(recipeId, dto));
    }

    @DeleteMapping(value = "/{recipeid}")
    public ResponseEntity<Void> deleteGroup(@PathVariable("recipeid") @NotNull Long recipeId) {
        recipeService.delete(recipeId);
        return ResponseEntity.noContent().build();
    }
}
