package com.pepa.befit.be_fit_be.api.enumeration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pepa.befit.be_fit_be.exception.CatMappingException;
import com.pepa.befit.be_fit_be.model.catalog.CatIngredientType;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum IngredientType {
    MEAT(1),
    FISH_AND_SEA(2),
    EGG(3),
    MILK_PRODUCT(4),
    CEREALS(5),
    LEGUMES(6),
    FRUIT(7),
    VEGETABLE(8),
    FLUID(9),
    ADDITIVE(10),
    NUT(11);

    @JsonIgnore
    private final Integer id;

    private static final Map<Integer, IngredientType> idMap = new HashMap<>();
    private static final Map<String, IngredientType> nameMap = new HashMap<>();

    static {
        for (IngredientType type : IngredientType.values()) {
            idMap.put(type.getId(), type);
            nameMap.put(type.name().toUpperCase(), type);
        }
    }

    IngredientType(final Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static IngredientType fromId(Integer id) {
        final IngredientType ingredientType = idMap.getOrDefault(id, null);
        if (ingredientType == null) {
            throw new CatMappingException(String.format("Missing Enum(%s) for id(%s)", IngredientType.class.getName(), id));
        }
        return ingredientType;
    }

    public static IngredientType fromName(String name) {
        final IngredientType ingredientType = nameMap.getOrDefault(name, null);
        if (ingredientType == null) {
            throw new CatMappingException(
                    String.format("Missing Enum(%s) for name(%s)", IngredientType.class.getName(), name));
        }
        return ingredientType;
    }

    public static IngredientType fromCatIngredientType(CatIngredientType catIngredientType) {
        return fromId(catIngredientType.getId());
    }

    public CatIngredientType toCatIngredientType() {
        return new CatIngredientType(getId(), name());
    }
}
