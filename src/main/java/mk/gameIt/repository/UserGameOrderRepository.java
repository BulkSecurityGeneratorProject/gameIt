package mk.gameIt.repository;

import mk.gameIt.domain.User;
import mk.gameIt.domain.UserGameOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Stefan on 30.8.2016.
 */
public interface UserGameOrderRepository extends JpaRepository<UserGameOrder, Long> {
    List<UserGameOrder> findAllByUser(User user);
}
