package vttp2022.Aug12.ssfassessment.Controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vttp2022.Aug12.ssfassessment.Model.News;
import vttp2022.Aug12.ssfassessment.Model.NewsFields;
import vttp2022.Aug12.ssfassessment.Service.NewsService;

@Controller
public class NewsController {

    @Autowired
    NewsService service;

    @PostConstruct
    public void getData(){
    
    }

    @GetMapping
    public String getArticlesPage(Model model){
        List<NewsFields> newsArticles = service.getArticles();
        model.addAttribute("newsarticles", newsArticles);
        return "newspage";
        }

    @PostMapping("/articles")
    public String save(@ModelAttribute News news, Model model){   
        service.saveArticles(news);  
    return "savedpage";    
    }
}
