package pc.my.befit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pc.my.befit.model.entity.Ingredient;
import org.springframework.stereotype.Repository;


@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
