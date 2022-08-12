package vttp2022.Aug12.ssfassessment.Service;

import vttp2022.Aug12.ssfassessment.Model.News;


public interface NewsRepo {

    public void saveArticles(News news);

    public News findById(String id);

}