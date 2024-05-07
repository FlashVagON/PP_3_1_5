package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsersList(){
        return userService.listUsers();
    }

    @GetMapping("/roleList")
    public List<Role> getRolesList(){
        return roleService.getAllRoles();
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<User> editUser(@Valid @RequestBody User user) {
        if (userService.showUser(user.getId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        if (userService.showUser(user.getId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(user);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername (Principal principal) {
        User user = userRepository.findByName(principal.getName());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
