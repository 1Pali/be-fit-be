package com.pepa.befit.be_fit_be.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.pepa.befit.be_fit_be.api.dto.AddIngredientDto;
import com.pepa.befit.be_fit_be.api.dto.IngredientDto;
import com.pepa.befit.be_fit_be.api.dto.RemovedItemDto;
import com.pepa.befit.be_fit_be.api.dto.UpdateIngredientDto;
import com.pepa.befit.be_fit_be.api.enumeration.DeletionStatus;
import com.pepa.befit.be_fit_be.api.service.IngredientService;
import com.pepa.befit.be_fit_be.exception.ElementDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @DeleteMapping(value = "/deletelist", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteIngredientList(@RequestBody List<Long> ingredientIdList) {
        if (ingredientIdList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty list of Ingredient Ids received");
        }

        List<RemovedItemDto> deletedIngredientList = new ArrayList<>();

        for (Long ingredientId : ingredientIdList) {
            try {
                ingredientService.delete(ingredientId);
                deletedIngredientList.add(new RemovedItemDto(ingredientId, DeletionStatus.SUCCESS, null));
            } catch (ElementDoesNotExistException e) {
//                log.error(e.getMessage(), e);
                deletedIngredientList.add(new RemovedItemDto(ingredientId, DeletionStatus.ERROR, e.getMessage()));
            }
        }
        return ResponseEntity.ok(new Gson().toJson(deletedIngredientList));
    }

}
