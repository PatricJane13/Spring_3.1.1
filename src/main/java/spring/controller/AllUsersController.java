package spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.model.News;
import spring.model.Role;
import spring.model.User;
import spring.service.NewsService;
import spring.service.UserService;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Api(value = "CRUD", description = "Полное управление пользователями...")
@Controller
@RequestMapping("/admin/")
public class AllUsersController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    UserService userService;

    @Autowired
    NewsService newsService;

    @ApiOperation(value = "Получение всех пользователей в List")
    @GetMapping(value = "all")
    public String loginJsp(Model model, Authentication authentication) {
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @PostMapping(value = "add")
    public String addUser(@ModelAttribute User user, @RequestParam String selectRole) throws IOException, SQLException {
        Role role = userService.getUserRole(selectRole);
        List<Role> list = new ArrayList<>();
        list.add(role);
        user.setRoles(list);
        if (!selectRole.isEmpty()) {
            userService.add(user);
        }
        return "redirect:/admin/all";
    }

    @PostMapping(value = "update")
    public String updateUser(@ModelAttribute User user,
                             @RequestParam String selectRole) throws IOException {
        Role role = userService.getUserRole(selectRole);
        User userId = userService.getUserById(user.getId());
        List<Role> list = userId.getRoles();
        if (!userId.getRoles().contains(role)) {
            list.add(role);
        }
        user.setRoles(list);
        if (!selectRole.isEmpty()) {
            userService.updateUser(user);
        }
        return "redirect:/admin/all";
    }

    @GetMapping(value = "delete")
    public String deleteUser(@RequestParam("id") Long id) throws IOException {
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        return "redirect:/admin/all";
    }

    @PostMapping(value = "addNews")
    public String addNews(@RequestParam MultipartFile imgNews, @RequestParam String textNews, @RequestParam String headerText) throws IOException, SQLException {
        if (!imgNews.isEmpty() && textNews != null) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + imgNews.getOriginalFilename();
            File file = new File(uploadPath + "/" + resultFileName);
            imgNews.transferTo(file);
            News news = new News(textNews, headerText,0L,  resultFileName, file.getAbsolutePath().replace("/home/patric/Project/ChertogyProgrammista/src/main/resources/static/",""));
            newsService.addNews(news);
        }
        return "redirect:/admin/all";
    }

}
