package pc.my.befit.api.dto;


import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import pc.my.befit.api.enumeration.DeletionStatus;

import javax.validation.constraints.NotNull;

@Value
public class RemovedItemDto {

    private Long id;
    private DeletionStatus state;
    private String description;
}
