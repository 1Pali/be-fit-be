package com.pepa.befit.be_fit_be.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pepa.befit.be_fit_be.api.dto.IngredientTypeDto;
import com.pepa.befit.be_fit_be.api.enumeration.IngredientType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ingredienttype")
public class IngredientTypeController {

    public IngredientTypeController() {
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IngredientTypeDto>> getListOfIngredientTypes() {
        final List<IngredientTypeDto> listOfIngredientTypes = Stream.of(IngredientType.values())
                .map(ingredientType -> new IngredientTypeDto(ingredientType.getId(), ingredientType.name()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listOfIngredientTypes);
    }
}
