package mk.gameIt.service.impl;

import mk.gameIt.GameItApplication;
import mk.gameIt.domain.NewsPost;
import mk.gameIt.repository.NewsPostRepository;
import mk.gameIt.service.NewsPostService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Stefan on 06.04.2016.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GameItApplication.class)
@WebAppConfiguration
public class NewsPostServiceImplTest {
    @Autowired
    NewsPostRepository newsPostRepository;

    @Autowired
    NewsPostService newsPostService;

    @Before
    public void setUp() throws Exception {
        newsPostRepository.deleteAll();
    }

    @After
    public void tearDown() throws Exception {

    }

    private NewsPost generateRandomNewsPost() {
        NewsPost post = new NewsPost();
        post.setPostTitle(UUID.randomUUID().toString());
        post.setPostDescription(UUID.randomUUID().toString());
        post.setPostAddDate(LocalDateTime.now());
        return post;
    }

    @Test
    public void findAll() throws Exception {
        List<NewsPost> list = new ArrayList<NewsPost>();
        NewsPost newsPost = generateRandomNewsPost();
        newsPostRepository.save(newsPost);
        list.add(newsPost);
        NewsPost newsPostTwo = generateRandomNewsPost();
        newsPostRepository.save(newsPostTwo);
        list.add(newsPostTwo);

        List<NewsPost> returnedList = newsPostService.findAll();

        assertEquals(list, returnedList);
    }

    @Test
    public void save() throws Exception {
        NewsPost post = generateRandomNewsPost();
        post = newsPostRepository.save(post);
        NewsPost post2 = newsPostService.findOne(post.getPostId());
        assertEquals(post, post2);
    }

    @Test
    public void findOne() throws Exception {
        NewsPost post = generateRandomNewsPost();
        post = newsPostRepository.save(post);
        NewsPost post2 = newsPostService.findOne(post.getPostId());
        assertEquals(post, post2);
    }

    @Test
    public void delete() throws Exception {
        NewsPost post = generateRandomNewsPost();
        post = newsPostRepository.save(post);
        newsPostService.delete(post.getPostId());
        assertNull(newsPostService.findOne(post.getPostId()));
    }
}