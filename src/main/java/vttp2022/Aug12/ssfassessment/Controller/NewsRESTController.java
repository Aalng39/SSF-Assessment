package vttp2022.Aug12.ssfassessment.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.Aug12.ssfassessment.Model.News;
import vttp2022.Aug12.ssfassessment.Service.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path = "/news", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsRESTController {

    @Autowired
    NewsService service;

    @GetMapping(path = "/{id}")
    public ResponseEntity<News> getGameBoardById(@PathVariable String id) {
        News news = service.findById(id);
        return ResponseEntity.ok(news);
    }

}
