package com.pepa.befit.be_fit_be.api.dto;


import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@JGlobalMap
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRecipeDto {
    @NotNull
    private Long id;

    @NotNull
    private String name;

}
