package mk.gameIt.repository;

import mk.gameIt.domain.UserGameOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stefan on 30.8.2016.
 */
public interface UserGameOrderRepository extends JpaRepository<UserGameOrder, Long> {
}
