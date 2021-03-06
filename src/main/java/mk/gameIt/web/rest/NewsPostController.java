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
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
            Sort sort = new Sort(Sort.Direction.DESC, "postAddDate");
            Pageable pageable = new PageRequest(page, size, sort);
            List<NewsPost> returnedList = newsPostService.findAll(pageable).getContent();
            return returnedList;
        } else {
            List<NewsPost> returnedList = newsPostService.findAll();
            return returnedList;
        }
    }

    @RequestMapping(value = "/news/tag/{tagName}", method = RequestMethod.GET)
    public ResponseEntity getAllNewsPosts(@PathVariable String tagName) {
        List<NewsPost> newsPosts = newsPostService.findAll();
        Tag tag = tagService.findOneByTagName(tagName);
        List<NewsPost> resultList = new ArrayList<>();
        for(NewsPost newsPost: newsPosts)  {
            if (newsPost.getTags().contains(tag)) {
                resultList.add(newsPost);
            }
        }

        if (resultList != null) {
            Collections.sort(resultList);
            return new ResponseEntity(HttpStatus.OK).ok(resultList);
        }

        return new ResponseEntity(HttpStatus.CONFLICT).ok().build();
    }

    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public NewsPost saveNewsPost(@RequestBody NewsPostObject newsPostObject) {
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
