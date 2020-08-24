package pc.my.befit.api.dto;


import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@JGlobalMap
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRecipeDto {

    @NotNull
    private String name;

}
