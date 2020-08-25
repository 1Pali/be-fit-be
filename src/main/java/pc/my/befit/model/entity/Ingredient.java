package pc.my.befit.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pc.my.befit.model.catalog.CatIngredientType;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INGREDIENT")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ENERGY")
    private Double energy;

    @Column(name = "PROTEIN")
    private Double protein;

    @Column(name = "CARBOHYDRATE")
    private Double carbohydrate;

    @Column(name = "FAT")
    private Double fat;

    @Column(name = "FIBER")
    private Double fiber;

    @Column(name = "PRICE")
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "INGREDIENT_TYPE_ID")
    private CatIngredientType ingredientType;
}
