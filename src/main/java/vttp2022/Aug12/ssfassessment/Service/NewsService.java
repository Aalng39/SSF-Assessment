package vttp2022.Aug12.ssfassessment.Service;

import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.Aug12.ssfassessment.Model.News;
import vttp2022.Aug12.ssfassessment.Model.NewsFields;
import vttp2022.Aug12.ssfassessment.Model.NewsList;

@Service
public class NewsService implements NewsRepo{

    @Value("${cryptoNews}")
    private String cryptoNewsURL;

    RestTemplate restTemplate = new RestTemplate();

    public List<NewsFields> getArticles(){
        ResponseEntity<String> response = restTemplate.getForEntity(cryptoNewsURL, String.class);
        String payload = response.getBody();
        StringReader stringReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(stringReader);
        JsonObject jsonObject = jsonReader.readObject();
        JsonArray jsonArray = jsonObject.getJsonArray("Data");

        for (int i = 0; i < jsonArray.size(); i++){
            JsonObject jsonOb = jsonArray.get(i).asJsonObject();

            NewsFields newsFields = new NewsFields();
            newsFields.setId(jsonOb.getString("id"));
            newsFields.setPublishedOn(jsonOb.getJsonNumber("published_on").toString());
            newsFields.setTitle(jsonOb.getString("title"));
            newsFields.setUrl(jsonOb.getString("url"));
            newsFields.setImageurl(jsonOb.getString("imageurl"));
            newsFields.setBody(jsonOb.getString("body"));
            newsFields.setTags(jsonOb.getString("tags"));
            newsFields.setCategories(jsonOb.getString("categories"));   
            NewsList.fieldsList.add(newsFields);
        }
        
        return NewsList.fieldsList;  
    }

    @Autowired
    RedisTemplate<String, News> redisTemplate;

    @Override
    public void saveArticles(News news) {
        System.out.println(news.getSavedArticles());
        redisTemplate.opsForList().rightPush(news.getDateTime(), (News) news.getSavedArticles());
        // redisTemplate.opsForValue().set(news.nFields.getId(), news);
        // News result = (News) redisTemplate.opsForValue().get(news.nFields.getId());
        // if (result != null) 
        //     return 1;
        // return 0;
       
    }

    @Override
    public News findById(String id) {
        News result = (News) redisTemplate.opsForValue().get(id);
            return result;
        
    }

}
