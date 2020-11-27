package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.model.News;
import spring.model.User;
import spring.service.NewsService;
import spring.service.UserService;

import java.awt.*;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/guest/")
public class GuestController {

    @Autowired
    UserService userService;

    @Autowired
    NewsService newsService;

    @GetMapping("getUser")
    public ResponseEntity<User> getUser(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "getNews")
    public ResponseEntity getNews(){
        List<News> newsList = newsService.listUsers();
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

}
