package mk.gameIt.repository;

import mk.gameIt.domain.NewsPost;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stefan on 03.04.2016.
 */
public interface NewsPostRepository extends JpaRepository<NewsPost,Long> {
}
