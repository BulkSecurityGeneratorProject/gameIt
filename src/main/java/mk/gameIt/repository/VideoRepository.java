package mk.gameIt.repository;

import mk.gameIt.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stefan on 31.03.2016.
 */
public interface VideoRepository extends JpaRepository<Video, Long> {
}
