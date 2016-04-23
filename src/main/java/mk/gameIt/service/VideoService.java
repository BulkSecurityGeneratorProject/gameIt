package mk.gameIt.service;

import mk.gameIt.domain.Video;

import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
public interface VideoService {
    Video findOne(Long id);

    List<Video> findAll();

    void delete(Long id);

    Video save(Video video);
}
