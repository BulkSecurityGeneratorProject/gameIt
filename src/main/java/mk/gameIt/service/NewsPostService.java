package mk.gameIt.service;

import mk.gameIt.domain.NewsPost;
import mk.gameIt.web.dto.NewsPostObject;
import mk.gameIt.web.dto.TagObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Stefan on 03.04.2016.
 */
public interface NewsPostService {
    List<NewsPost> findAll();
    Page<NewsPost> findAll(Pageable pageable);
    NewsPost save(NewsPost newsPost, TagObject tagObject);

    NewsPost findOne(Long id);
    NewsPost incrementNumberOfViews(NewsPost newsPost);
    void deleteAll();

    void delete(Long id);

    NewsPost update(Long id, NewsPostObject newsPostObject);
}
