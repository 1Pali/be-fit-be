package com.pepa.befit.be_fit_be.repository;

import com.pepa.befit.be_fit_be.model.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
