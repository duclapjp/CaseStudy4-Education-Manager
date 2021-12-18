package com.codegym.educationmanager.controller;

import com.codegym.educationmanager.model.role.Role;
import com.codegym.educationmanager.model.user.User;
import com.codegym.educationmanager.model.user.UserForm;
import com.codegym.educationmanager.service.role.IRoleService;
import com.codegym.educationmanager.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
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

    @GetMapping("/page")
    public ResponseEntity<Page<User>> findAll(@PageableDefault(size = 6) Pageable pageable) {
        return new ResponseEntity<>(userService.pageUser(pageable), HttpStatus.OK);
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
    public ResponseEntity<Iterable<User>> getMinistry(@PathVariable("role") Long id) {
        Optional<Role> role1 = roleService.findById(id);
        Iterable<User> users = userService.findUserByRole(role1);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //tao moi ministry
    @PostMapping("/createMinistry")
    public ResponseEntity<User> createMinistry(@RequestBody UserForm userForm) {
        MultipartFile multipartFile = userForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(userForm.getImage().getBytes(), new File(fileName+filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPhone(userForm.getPhone());
//        user.setCode(userForm.getCode());
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        user.setImage(fileName);
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    //cap nhat ministry
    @PutMapping("/updateMinistry/{id}")
    public ResponseEntity<User> editUser(@PathVariable ("id") Long id, UserForm userForm) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            MultipartFile userFormImage = userForm.getImage();
            String fileName = userFormImage.getOriginalFilename();
            try {
                FileCopyUtils.copy(userFormImage.getBytes(), new File(fileName + filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            User user = new User();
            user.setName(userOptional.get().getName());
            user.setEmail(userOptional.get().getEmail());
            user.setPhone(userOptional.get().getPhone());
            user.setUsername(userOptional.get().getUsername());
            user.setPassword(userOptional.get().getPassword());
            user.setImage(fileName);
            return new ResponseEntity<>(userService.save(user),HttpStatus.OK);
        }
    }
}
