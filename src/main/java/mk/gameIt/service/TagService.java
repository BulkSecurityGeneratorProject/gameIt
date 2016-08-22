package mk.gameIt.service;

import mk.gameIt.domain.Tag;

import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
public interface TagService {
    Tag findOne(Long id);
    List<Tag> findAll();
    Tag findOneByTagName(String tagName);
    void delete(Long id);
    Tag save(Tag tag);
}
