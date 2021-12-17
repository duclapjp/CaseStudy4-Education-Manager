package com.codegym.educationmanager.controller;

import com.codegym.educationmanager.model.role.Role;
import com.codegym.educationmanager.model.user.User;
import com.codegym.educationmanager.model.user.UserForm;
import com.codegym.educationmanager.service.role.IRoleService;
import com.codegym.educationmanager.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Value("${file-path}")
    private String filePath;

    @GetMapping
    public ResponseEntity<Iterable<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@Validated @RequestBody UserForm userForm, BindingResult bindingResult) {
        if (!bindingResult.hasFieldErrors()) {
            if (userForm.getImage().isEmpty()) {
                String fileName = userForm.getImage().getOriginalFilename();
                Path path = Paths.get(filePath);
                try {
                    InputStream inputStream = userForm.getImage().getInputStream();
                    Files.copy(inputStream, path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                User user = new User(userForm.getName(), userForm.getEmail(), userForm.getPhone(), userForm.getUsername(), userForm.getPassword(), fileName, userForm.getRole());
                userService.save(user);
                return new ResponseEntity<>(user, HttpStatus.CREATED);
            } else {
                User use = new User(userForm.getName(), userForm.getEmail(), userForm.getPhone(), userForm.getUsername(), userForm.getPassword(), userForm.getRole());
                userService.save(use);
                return new ResponseEntity<>(use, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/ministryByRole/{role}")
    public ResponseEntity<Iterable<User>> getMinistry(@PathVariable ("role") Long id) {
        Optional<Role> role1 = roleService.findById(id);
        Iterable<User> users = userService.findUserByRole(role1);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/create-ministries")
    public ModelAndView showCreateMinistryForm(){
        ModelAndView modelAndView = new ModelAndView("/ministry/create");
        modelAndView.addObject("ministries", new User());
        return modelAndView;
    }

    @PostMapping("/create_ministry")
    public ModelAndView createMinistry(@ModelAttribute ("ministry") User user){
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/ministry/create");
        modelAndView.addObject("ministries", user);
        modelAndView.addObject("message", "successful create");
        return modelAndView;
    }

}
