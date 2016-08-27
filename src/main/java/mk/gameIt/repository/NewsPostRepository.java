package mk.gameIt.repository;

import mk.gameIt.domain.NewsPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Stefan on 03.04.2016.
 */
public interface NewsPostRepository extends JpaRepository<NewsPost,Long> {

}

