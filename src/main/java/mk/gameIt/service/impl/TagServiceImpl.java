package mk.gameIt.service.impl;

import mk.gameIt.domain.Tag;
import mk.gameIt.repository.TagRepository;
import mk.gameIt.service.TagService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
@Service
public class TagServiceImpl implements TagService {
    private final Logger log = org.slf4j.LoggerFactory.getLogger(TagServiceImpl.class);

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
    public Tag findOneByTagName(String tagName) {
        return tagRepository.findOneByTagName(tagName);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        //Tag tag = tagRepository.findOne(id);
        //log.debug("Deleted Tag: {}", tag);
        tagRepository.delete(id);
    }

    @Transactional
    @Override
    public Tag save(Tag tag) {
        tag = tagRepository.save(tag);
        log.debug("Created Tag: {}", tag);
        return tag;
    }
}
