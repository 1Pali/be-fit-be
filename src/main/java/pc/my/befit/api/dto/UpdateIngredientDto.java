package pc.my.befit.api.dto;


import com.googlecode.jmapper.annotations.JGlobalMap;
import com.googlecode.jmapper.annotations.JMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@JGlobalMap
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateIngredientDto {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Double energy;

    @NotNull
    private Double protein;

    @NotNull
    private Double carbohydrate;

    @NotNull
    private Double fat;

    @NotNull
    private Double fiber;

    @NotNull
    private Double price;

    @NotNull
    private Integer idIngredientType;
}
