package mk.gameIt.web.rest;

import mk.gameIt.domain.Game;
import mk.gameIt.domain.NewsPost;
import mk.gameIt.domain.Tag;
import mk.gameIt.service.NewsPostService;
import mk.gameIt.service.TagService;
import mk.gameIt.web.dto.GameObject;
import mk.gameIt.web.dto.NewsPostObject;
import mk.gameIt.web.dto.TagObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 03.04.2016.
 */
@RestController
@RequestMapping("/api")
public class NewsPostController {
    @Autowired
    private NewsPostService newsPostService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public List<NewsPost> getAllNewsPosts(@RequestParam(value = "page", required = false) Integer page,
                                          @RequestParam(value = "size", required = false) Integer size) {
        if (page != null && size != null) {
            Pageable pageable = new PageRequest(page, size);
            List<NewsPost> returnedList = newsPostService.findAll(pageable).getContent();

            return returnedList;
        } else {
            List<NewsPost> returnedList = newsPostService.findAll();
            return returnedList;
        }
    }


    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public NewsPost saveNewsPost(@RequestBody NewsPostObject newsPostObject) {
        System.out.println(newsPostObject.getNewsPost());
        System.out.println(newsPostObject.getTagObject().getTagName());
        return newsPostService.save(newsPostObject.getNewsPost(), newsPostObject.getTagObject());
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
    public NewsPost getOneNewsPost(@PathVariable Long id) {
        NewsPost post = newsPostService.findOne(id);
        post = newsPostService.incrementNumberOfViews(post);
    return post;
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.DELETE)
    public void deleteOneNewsPost(@PathVariable Long id) {
        newsPostService.delete(id);
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.PUT)
    public NewsPost updateOneNewsPost(@PathVariable Long id, @RequestBody NewsPostObject newsPostObject) {
        return newsPostService.update(id, newsPostObject);
    }
}
