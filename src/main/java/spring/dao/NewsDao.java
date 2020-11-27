package spring.dao;

import spring.model.Categories;
import spring.model.News;
import spring.model.User;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public interface NewsDao {
    void addNews(News news) throws SQLException;

    List<News> listNews();

    News getNewsById(Long id);

    void updateNews(User user);

    void deleteNews(User user);

    List<News> getNewsByCategory(Categories categories);

}
