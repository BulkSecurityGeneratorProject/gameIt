package mk.gameIt.service;

import mk.gameIt.domain.NewsPost;

import java.util.List;

/**
 * Created by Stefan on 03.04.2016.
 */
public interface NewsPostService {
    List<NewsPost> findAll();

    NewsPost save(NewsPost newsPost);
}
