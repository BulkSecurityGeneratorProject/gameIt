package mk.gameIt.service.impl;

import mk.gameIt.domain.Category;
import mk.gameIt.repository.CategoryRepository;
import mk.gameIt.service.CategoryService;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private final Logger log = org.slf4j.LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        //   Category category = categoryRepository.findOne(id);
        //   log.debug("Deleted Category: {}", category);
        categoryRepository.delete(id);
    }

    @Transactional
    @Override
    public Category save(Category category) {
        category = categoryRepository.save(category);
        log.debug("Created Category: {}", category);
        return category;
    }
}
