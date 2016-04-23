package mk.gameIt.service;

import mk.gameIt.domain.Genre;

import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
public interface GenreService {
    Genre findOne(Long id);
    List<Genre> findAll();
    void delete(Long id);
    Genre save(Genre genre);

}
