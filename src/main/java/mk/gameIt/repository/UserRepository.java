package mk.gameIt.repository;

import mk.gameIt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stefan on 24.03.2016.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findOneByUsername(String username);
}
