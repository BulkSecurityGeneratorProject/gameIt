package mk.gameIt.service.impl;

import mk.gameIt.domain.Genre;
import mk.gameIt.repository.GenreRepository;
import mk.gameIt.service.GenreService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
@Service
public class GenreServiceImpl implements GenreService {
    private final Logger log = org.slf4j.LoggerFactory.getLogger(GenreServiceImpl.class);

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre findOne(Long id) {
        return genreRepository.findOne(id);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        //Genre genre = genreRepository.findOne(id);
        //log.debug("Deleted Genre: {}",genre);
        genreRepository.delete(id);
    }

    @Transactional
    @Override
    public Genre save(Genre genre) {
        genre = genreRepository.save(genre);
        log.debug("Created Genre: {}", genre);
        return genre;
    }
}
