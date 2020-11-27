package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.NewsDao;
import spring.model.News;
import spring.model.User;

import java.sql.SQLException;
import java.util.List;

@Service
public class NewsServiceImp implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Transactional
    @Override
    public void addNews(News news) throws SQLException {
        newsDao.addNews(news);
    }

    @Transactional
    @Override
    public List<News> listUsers() {
        return newsDao.listNews();
    }

    @Transactional
    @Override
    public User getNewsById(Long id) {
        return null;
    }

    @Transactional
    @Override
    public void updateUNews(News news) {

    }

    @Transactional
    @Override
    public void deleteNews(News news) {

    }
}
