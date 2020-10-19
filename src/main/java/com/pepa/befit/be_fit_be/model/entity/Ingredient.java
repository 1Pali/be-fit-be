package com.pepa.befit.be_fit_be.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pepa.befit.be_fit_be.model.catalog.CatIngredientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "INGREDIENT_IMAGE_ID")
    private File ingredientImage;
}
