package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    @Secured("ADMIN")
    public String adminPage(ModelMap model, Principal principal) {
        model.addAttribute("usersList", userService.listUsers());
        model.addAttribute("userModel", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "admin";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return "redirect:/admin";
    }

//    @GetMapping("/edit")
//    public String editUser(Model model, @RequestParam("id") Long id) {
//        model.addAttribute("user", userService.showUser(id));
//        return "edit";
//    }

//    @GetMapping("/new")
//    public String newUser(@ModelAttribute("user") User user, ModelMap model) {
//        model.addAttribute("userNew", new User());
//        return "new";
//    }
}
