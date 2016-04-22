package mk.gameIt.repository;

import mk.gameIt.domain.CommentHardwareProduct;
import mk.gameIt.domain.CommentHardwareProductId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stefan on 23.04.2016.
 */
public interface CommentHardwareProductRepository extends JpaRepository<CommentHardwareProduct,CommentHardwareProductId> {

}
