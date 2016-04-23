package mk.gameIt.repository;

import mk.gameIt.domain.HardwareProductRating;
import mk.gameIt.domain.HardwareProductRatingId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stefan on 23.04.2016.
 */
public interface HardwareProductRatingRepository extends JpaRepository<HardwareProductRating,HardwareProductRatingId> {
}
