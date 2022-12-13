package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findUserBYId(Long id);
    List<User> allUsers();

    boolean saveUser(User user);

    boolean deleteUser(Long userId);

    boolean updateUser(Long userId, User user);

    User findByUsername(String username);


}
