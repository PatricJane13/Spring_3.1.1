package spring.service;

import spring.model.News;
import spring.model.User;

import java.sql.SQLException;
import java.util.List;

public interface NewsService {
    void addNews(News mews) throws SQLException;

    List<News> listUsers();

    User getNewsById(Long id);

    void updateUNews(News news);

    void deleteNews(News news);
}
