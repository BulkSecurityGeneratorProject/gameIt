package mk.gameIt.service.impl;

import mk.gameIt.domain.Game;
import mk.gameIt.domain.NewsPost;
import mk.gameIt.domain.Tag;
import mk.gameIt.repository.NewsPostRepository;
import mk.gameIt.service.NewsPostService;
import mk.gameIt.service.TagService;
import mk.gameIt.web.dto.NewsPostObject;
import mk.gameIt.web.dto.TagObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Stefan on 03.04.2016.
 */
@Service
@Transactional
public class NewsPostServiceImpl implements NewsPostService {
    private final Logger log = org.slf4j.LoggerFactory.getLogger(NewsPostServiceImpl.class);
    @Autowired
    TagService tagService;

    @Autowired
    NewsPostRepository newsPostRepository;

    @Override
    public List<NewsPost> findAll() {
        List<NewsPost> newsPostList = newsPostRepository.findAll();
        return newsPostList;
    }

    @Override
    public Page<NewsPost> findAll(Pageable pageable) {
        Page<NewsPost> newsPostList = newsPostRepository.findAll(pageable);
        return newsPostList;

    }

    @Override
    @Transactional
    public NewsPost save(NewsPost newPost, TagObject tagObject) {
        NewsPost post = new NewsPost();
        post.setPostDescription(newPost.getPostDescription());
        post.setSmallDescription(newPost.getSmallDescription());
        post.setPostAddDate(newPost.getPostAddDate());
        post.setPublishedPicturePath(newPost.getPublishedPicturePath());

        if (post.getPostAddDate() == null) {
            post.setPostAddDate(LocalDateTime.now());
        }
        if (post.getTags() == null) {
            post.setTags(new ArrayList<>());
        }

        post.setPostTitle(newPost.getPostTitle());
        post = newsPostRepository.save(post);

        if (tagObject.getTagName() != null) {
            String[] tags = tagObject.getTagName().split(",");
            for (String tagName : tags) {
                Tag existingTag = tagService.findOneByTagName(tagName.toLowerCase());
                if (existingTag == null) {
                    existingTag = new Tag();
                    existingTag.setTagName(tagName.toLowerCase());
                    existingTag.setPost(new ArrayList<>());
                    existingTag = tagService.save(existingTag);
                }
                existingTag.getPost().add(post);
                post.getTags().add(existingTag);

                tagService.save(existingTag);
            }
            newsPostRepository.save(post);
        }

        log.debug("Created NewsPost: {}", post);
        return post;
    }

    @Override
    public NewsPost findOne(Long id) {
        return newsPostRepository.findOne(id);
    }

    @Override
    public synchronized NewsPost incrementNumberOfViews(NewsPost newsPost) {
        newsPost.setPostNumberOfViews(newsPost.getPostNumberOfViews()+1);
        return newsPostRepository.save(newsPost);
    }

    @Override
    @Transactional
    public void deleteAll() {
        newsPostRepository.deleteAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        //log.debug("Deleted NewsPost: {}", newsPost);
        newsPostRepository.delete(id);
    }

    @Override
    public NewsPost update(Long id, NewsPostObject newsPostObject) {
        NewsPost newsPost1 = newsPostRepository.findOne(id);
        newsPost1.setSmallDescription(newsPostObject.getNewsPost().getSmallDescription());
        newsPost1.setPublishedPicturePath(newsPostObject.getNewsPost().getPublishedPicturePath());
        newsPost1.setPostDescription(newsPostObject.getNewsPost().getPostDescription());
        return newsPostRepository.save(newsPost1);
    }



}
