package mk.gameIt.repository;

import mk.gameIt.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stefan on 20.8.2016.
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
}
