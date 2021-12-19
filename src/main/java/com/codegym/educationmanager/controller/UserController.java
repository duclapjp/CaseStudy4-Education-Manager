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
@RequestMapping("/user")
@CrossOrigin("*")
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
    @GetMapping("/page/{roleName}")
    public ResponseEntity<Page<User>> findAll(@PageableDefault(size = 6) Pageable pageable, @PathVariable String roleName) {
        Role role = roleService.findRoleByName(roleName);
        return new ResponseEntity<>(userService.findAllByRole(role, pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id).get(), HttpStatus.OK);
    }
    @GetMapping("/createForm")
    public ModelAndView createForm(){
        Iterable<Role> roles = roleService.findAll();
        ModelAndView modelAndView = new ModelAndView("ministry/create");
        modelAndView.addObject("userForm", new UserForm());
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }
    @GetMapping("/createAdminForm")
    public ModelAndView createAdminForm(){
        Iterable<Role> roles = roleService.findAll();
        ModelAndView modelAndView = new ModelAndView("admin/create");
        modelAndView.addObject("userForm", new UserForm());
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }
    @PostMapping("/ministry")
    public ModelAndView saveUser(@Validated @ModelAttribute UserForm userForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("ministry/create");
        if (!bindingResult.hasFieldErrors()) {
            if (userForm.getImage() != null) {
                String fileName = userForm.getImage().getOriginalFilename();
                Path path = Paths.get(filePath);
                try {
                    InputStream inputStream = userForm.getImage().getInputStream();
                    Files.copy(inputStream, path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                User user = new User(userForm.getName(), userForm.getEmail(), userForm.getPhone(), userForm.getUsername(), userForm.getPassword(),userForm.getCode(), fileName, userForm.getRole());
                userService.save(user);
                modelAndView.addObject("userForm", userForm);
                modelAndView.addObject("roles", roleService.findAll());
                modelAndView.addObject("message", "Tao moi thanh cong");
                return modelAndView;
            } else {
                User use = new User(userForm.getName(), userForm.getEmail(), userForm.getPhone(), userForm.getUsername(), userForm.getPassword(),userForm.getCode(), userForm.getRole());
                userService.save(use);
                modelAndView.addObject("userForm", userForm);
                modelAndView.addObject("roles", roleService.findAll());
                modelAndView.addObject("message", "Tao moi thanh cong");
                return modelAndView;
            }
        }
        modelAndView.addObject("userForm", userForm);
        modelAndView.addObject("roles", roleService.findAll());
        return modelAndView;
    }
    @PostMapping("/admin")
    public ModelAndView saveAdmin(@Validated @ModelAttribute UserForm userForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("admin/create");
        if (!bindingResult.hasFieldErrors()) {
            if (userForm.getImage() != null) {
                String fileName = userForm.getImage().getOriginalFilename();
                Path path = Paths.get(filePath);
                try {
                    InputStream inputStream = userForm.getImage().getInputStream();
                    Files.copy(inputStream, path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                User user = new User(userForm.getName(), userForm.getEmail(), userForm.getPhone(), userForm.getUsername(), userForm.getPassword(),userForm.getCode(), fileName, userForm.getRole());
                userService.save(user);
                modelAndView.addObject("userForm", userForm);
                modelAndView.addObject("roles", roleService.findAll());
                modelAndView.addObject("message", "Tao moi thanh cong");
                return modelAndView;
            } else {
                User use = new User(userForm.getName(), userForm.getEmail(), userForm.getPhone(), userForm.getUsername(), userForm.getPassword(),userForm.getCode(), userForm.getRole());
                userService.save(use);
                modelAndView.addObject("userForm", userForm);
                modelAndView.addObject("roles", roleService.findAll());
                modelAndView.addObject("message", "Tao moi thanh cong");
                return modelAndView;
            }
        }
        modelAndView.addObject("userForm", userForm);
        modelAndView.addObject("roles", roleService.findAll());
        return modelAndView;
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

    @PutMapping("/{id}/{newPass}/{oldPass}")
    public ResponseEntity<String> editPass(@PathVariable Long id,@PathVariable String newPass,@PathVariable String oldPass) {
        Optional<User> user1 = userService.findById(id);
        if (user1.get().getPassword().equals(oldPass)) {
            user1.get().setPassword(newPass);
            userService.save(user1.get());
            return new ResponseEntity<>("lêu lêu thành công", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("lêu lêu không thành công", HttpStatus.OK);
        }
    }
}
