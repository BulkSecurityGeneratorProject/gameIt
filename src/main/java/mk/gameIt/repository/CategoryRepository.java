package mk.gameIt.repository;

import mk.gameIt.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stefan on 23.04.2016.
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
