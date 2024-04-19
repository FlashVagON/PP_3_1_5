package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MainRestController {

    final UserService userService;
    final RoleService roleService;


    @GetMapping("/userlist")
    public List<User> getUsersList(){
        return userService.listUsers();
    }
    @GetMapping("/rolelist")
    public List<Role> getRolesList(){
        return roleService.getAllRoles();
    }
    @PostMapping("/adduser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/edituser")
    public ResponseEntity<User> editUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/deleteuser")
    public ResponseEntity<String> deleteUser(@RequestBody Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }
}
