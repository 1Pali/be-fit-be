package pc.my.befit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pc.my.befit.model.entity.Recipe;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
