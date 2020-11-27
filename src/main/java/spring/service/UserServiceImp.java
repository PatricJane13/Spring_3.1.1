package spring.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import spring.dao.UserDao;
import spring.model.Role;
import spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    ZoneId zid1 = ZoneId.of("+03:00:00");

    @Transactional
    @Override
    public void add(User user) throws SQLException {
        user.setRegistrationDate(LocalDateTime.now(zid1));
        System.out.println(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Transactional
    @Override
    public User findByUsername(String name) {
        return userDao.findByUsername(name);
    }

    @Transactional
    @Override
    public Role getUserRole(String role) {
        return userDao.getUserRole(role);
    }

    @Transactional
    @Override
    public boolean isExistsUser(String email){
        return userDao.isExistsUser(email);
    }
}
