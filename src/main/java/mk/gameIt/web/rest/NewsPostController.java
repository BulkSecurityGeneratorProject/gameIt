package mk.gameIt.web.rest;

import mk.gameIt.domain.NewsPost;
import mk.gameIt.service.NewsPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
