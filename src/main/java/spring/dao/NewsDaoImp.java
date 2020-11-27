package spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import spring.model.Categories;
import spring.model.News;
import spring.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NewsDaoImp implements NewsDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addNews(News news) throws SQLException {
        entityManager.persist(news);
    }

    @Override
    public List<News> listNews() {
        Query query = entityManager.createQuery("FROM News");
        return (List<News>) query.getResultList();
    }

    @Override
    public News getNewsById(Long id) {
        return null;
    }

    @Override
    public void updateNews(User user) {

    }

    @Override
    public void deleteNews(User user) {

    }

    @Override
    public List<News> getNewsByCategory(Categories categories) {
        Query query = entityManager.createQuery("FROM News WHERE News.categories= :categories"); //Пока тестится, непонятно работает или нет!
        query.setParameter("categories", categories);
        return query.getResultList();
    }

}
