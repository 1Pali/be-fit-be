package pc.my.befit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pc.my.befit.model.entity.File;

public interface FileRepository extends JpaRepository<File, Long>{
}
