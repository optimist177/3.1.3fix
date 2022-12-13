package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@Controller
public class AdminController {


    private final UserService userService;

    private final RoleService roleService ;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String getUserLsit(Model model) {
        List<User>  userList = userService.allUsers();
        model.addAttribute("userList",userList);
        return "/list";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model ) {
        model.addAttribute("user",userService.findUserBYId(id));
        return "/edit";
    }

    @GetMapping("/create")
    public String register(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("listRoles",roleService.listRoles());
        return "/create";
    }

    @PostMapping("/create")
    public String performRegister(@ModelAttribute("user") User user,
                                 @RequestParam("roles") Long[] rolesId) {
        for  (Long id : rolesId ) {
            user.addRole(roleService.getRole(id));
        }
        if (userService.saveUser(user)) {
            return "/user";
        }
        return "/user";
    }
    @PatchMapping("/update/{id}")
    public String update(@ModelAttribute User user, @PathVariable("id") Long id) {
        userService.updateUser(id,user);
        return "redirect:/users";
    }
}