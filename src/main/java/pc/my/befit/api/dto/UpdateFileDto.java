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
public class UpdateFileDto {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private byte[] file;
}
