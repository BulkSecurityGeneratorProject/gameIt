package mk.gameIt.repository;

import mk.gameIt.domain.Location;
import mk.gameIt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Stefan on 20.8.2016.
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByUserId(User userId);
}
