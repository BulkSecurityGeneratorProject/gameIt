package mk.gameIt.service;

import mk.gameIt.domain.Category;

import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
public interface CategoryService {
    Category findOne(Long id);
    List<Category> findAll();
    void delete(Long id);
    Category save(Category category);
}
