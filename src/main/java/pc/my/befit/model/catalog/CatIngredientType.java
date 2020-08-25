package pc.my.befit.model.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Immutable
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CAT_INGREDIENT_TYPE")
public class CatIngredientType {
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "NAME")
    private String name;
}
