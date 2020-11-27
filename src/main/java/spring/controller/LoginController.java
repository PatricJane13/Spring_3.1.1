package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.model.Role;
import spring.model.User;
import spring.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping(value = "addUserReg")
    public String registration(@RequestParam String nickName,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String passwordTwo) throws SQLException {
        if(password.equals(passwordTwo) && !userService.isExistsUser(email)){
            User user = new User();
            Role role = userService.getUserRole("USER");
            List<Role> list = new ArrayList<>();
            list.add(role);
            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(nickName);
            user.setRoles(list);
            userService.add(user);
            return "redirect:/login";
        }
        return "redirect:/registration";
    }

    @GetMapping(value = "user")
    public String userPage(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("getUserOrAdminPage")
    public String getUserOrAdminPage(Authentication authentication, Model model){
        User user = (User) authentication.getPrincipal();
        List<Role> list = user.getRoles();
        model.addAttribute("user", user);
        for (Role role : list){
            if (role.getRole().equals("ADMIN")){
                return "redirect:/admin/all";
            }else if (role.getRole().equals("USER")){
                return "redirect:/user";
            }
        }
        return "redirect:/login";
    }

    @GetMapping(value = "home")
    public String homePage() {
        return "home";
    }
}
