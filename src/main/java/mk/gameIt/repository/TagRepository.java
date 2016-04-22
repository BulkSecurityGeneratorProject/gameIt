package mk.gameIt.repository;

import mk.gameIt.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stefan on 16.04.2016.
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
   Tag findOneByTagName(String tagName);

}
