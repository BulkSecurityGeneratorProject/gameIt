package mk.gameIt.service.impl;

import mk.gameIt.domain.Tag;
import mk.gameIt.repository.TagRepository;
import mk.gameIt.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;
    @Override
    public Tag findOne(Long id) {
        return tagRepository.findOne(id);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        tagRepository.delete(id);
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }
}
