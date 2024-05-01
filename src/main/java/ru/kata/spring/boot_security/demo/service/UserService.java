package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    void addUser(User user);
    void updateUser(User user);
    User showUser(Long id);
    void deleteUser(User user);
    List<User> listUsers();
}
