package mk.gameIt.web.rest;

import mk.gameIt.domain.NewsPost;
import mk.gameIt.service.NewsPostService;
import mk.gameIt.web.dto.NewsPostObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Stefan on 03.04.2016.
 */
@RestController
@RequestMapping("/api")
public class NewsPostController {
    @Autowired
    private NewsPostService newsPostService;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public List<NewsPost> getAllNewsPosts(){
        return newsPostService.findAll();
    }
    @RequestMapping(value="/news", method = RequestMethod.POST)
    public NewsPost saveNewsPost(@RequestBody NewsPost newsPost ){
        return newsPostService.save(newsPost);
    }
    @RequestMapping(value ="/news/{id}", method = RequestMethod.GET)
    public NewsPost getOneNewsPost(@PathVariable Long id){
        return newsPostService.findOne(id);
    }
    @RequestMapping(value = "/news/{id}", method = RequestMethod.DELETE)
    public void deleteOneNewsPost(@PathVariable Long id){
        newsPostService.delete(id);
    }
    @RequestMapping(value="/news/{id}", method = RequestMethod.PUT)
    public NewsPost updateOneNewsPost(@PathVariable Long id, @RequestBody NewsPostObject newsPostObject){
        return newsPostService.update(id, newsPostObject);
    }
}
