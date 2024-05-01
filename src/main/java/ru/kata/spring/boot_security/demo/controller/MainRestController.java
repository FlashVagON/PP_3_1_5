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
public class MainRestController {

    final UserService userService;
    final RoleService roleService;
    final UserRepository userRepository;


    @GetMapping("/userList")
    public List<User> getUsersList(){
        return userService.listUsers();
    }
    @GetMapping("/roleList")
    public List<Role> getRolesList(){
        return roleService.getAllRoles();
    }
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/editUser")
    public ResponseEntity<User> editUser(@Valid @RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername (Principal principal) {
        User user = userRepository.findByName(principal.getName());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


}
