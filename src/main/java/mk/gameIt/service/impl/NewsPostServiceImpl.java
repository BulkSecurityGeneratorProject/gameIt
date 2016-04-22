package mk.gameIt.service.impl;

import mk.gameIt.domain.NewsPost;
import mk.gameIt.repository.NewsPostRepository;
import mk.gameIt.service.NewsPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Stefan on 03.04.2016.
 */
@Service
public class NewsPostServiceImpl implements NewsPostService {
    @Autowired
    NewsPostRepository newsPostRepository;
    @Override
    public List<NewsPost> findAll() {
      return newsPostRepository.findAll();
    }

    @Override
    public NewsPost save(NewsPost newsPost) {
        NewsPost post = new NewsPost();
        post.setPostDescription(newsPost.getPostDescription());
        post.setPostAddDate(newsPost.getPostAddDate());
        newsPost.setPublishedPicturePath(newsPost.getPublishedPicturePath());
        newsPost.setTags(newsPost.getTags());
        if(post.getPostAddDate() == null){
            post.setPostAddDate(LocalDateTime.now());
        }

        post.setPostTitle(newsPost.getPostTitle());
        return newsPostRepository.save(post);
    }

    @Override
    public NewsPost findOne(Long id) {
        return newsPostRepository.findOne(id);
    }

    @Override
    public void deleteAll() {
        newsPostRepository.deleteAll();
    }

    @Override
    public void delete(Long id) {
        newsPostRepository.delete(id);
    }

    @Override
    public NewsPost update(Long id, NewsPost newsPost) {
        NewsPost newsPost1 = newsPostRepository.findOne(id);
        //TODO:  FINISH UPDATING NEWS POST
        // newsPost1.set(newsPost.get);
        return newsPostRepository.save(newsPost1);
    }


}
