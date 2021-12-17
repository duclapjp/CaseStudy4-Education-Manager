package com.codegym.educationmanager.controller;

import com.codegym.educationmanager.model.user.User;
import com.codegym.educationmanager.model.user.UserForm;
import com.codegym.educationmanager.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Value("${file-path}")
    private String filePath;

    @GetMapping
    public ResponseEntity<Iterable<User>> findAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<User>> findAll(@PageableDefault(size = 6) Pageable pageable){
        return new ResponseEntity<>(userService.pageUser(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findById(id).get(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<User> saveUser(@Validated @RequestBody UserForm userForm, BindingResult bindingResult){
        if (!bindingResult.hasFieldErrors()){
            if (userForm.getImage().isEmpty()){
                String fileName = userForm.getImage().getOriginalFilename();
                Path path = Paths.get(filePath);
                try {
                    InputStream inputStream = userForm.getImage().getInputStream();
                    Files.copy(inputStream, path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                }catch (Exception e){
                    e.printStackTrace();
                }
                User user = new User(userForm.getName(), userForm.getEmail(), userForm.getPhone(), userForm.getUsername(), userForm.getPassword(), fileName, userForm.getRole());
                userService.save(user);
                return new ResponseEntity<>(user, HttpStatus.CREATED);
            }
            else {
                User use = new User(userForm.getName(), userForm.getEmail(), userForm.getPhone(), userForm.getUsername(), userForm.getPassword(), userForm.getRole());
                userService.save(use);
                return new ResponseEntity<>(use, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
