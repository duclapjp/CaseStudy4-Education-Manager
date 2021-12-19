package com.codegym.educationmanager.controller;

import com.codegym.educationmanager.model.grade.Grade;
import com.codegym.educationmanager.model.role.Role;
import com.codegym.educationmanager.model.user.User;
import com.codegym.educationmanager.model.user.UserForm;
import com.codegym.educationmanager.service.grade.IGradeService;
import com.codegym.educationmanager.service.role.IRoleService;
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
import org.springframework.web.servlet.ModelAndView;

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
    @Autowired
    private IGradeService gradeService;
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
    public ModelAndView createForm() {
        Iterable<Role> roles = roleService.findAll();
        ModelAndView modelAndView = new ModelAndView("ministry/create");
        modelAndView.addObject("userForm", new UserForm());
        modelAndView.addObject("grades", gradeService.findAll());
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }

    @GetMapping("/createAdminForm")
    public ModelAndView createAdminForm() {
        Iterable<Role> roles = roleService.findAll();
        ModelAndView modelAndView = new ModelAndView("admin/create1");
        modelAndView.addObject("userForm", new UserForm());
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }

    @PostMapping("/ministry")
    public ModelAndView saveUser(@Validated @ModelAttribute UserForm userForm, @RequestParam("gradeId") Long gradeId, BindingResult bindingResult) {
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
                User user = new User(userForm.getName(), userForm.getEmail(), userForm.getPhone(), userForm.getUsername(), userForm.getPassword(), fileName, userForm.getCode(), userForm.getRole());
                userService.save(user);
                Grade grade = gradeService.findById(gradeId).get();
                grade.getUser().add(user);
                gradeService.save(grade);
                ModelAndView modelAndView = new ModelAndView("ministry/create");
                modelAndView.addObject("userForm", userForm);
                modelAndView.addObject("grades", gradeService.findAll());
                modelAndView.addObject("roles", roleService.findAll());
                modelAndView.addObject("message", "Tao moi thanh cong");
                return modelAndView;
            } else {
                User user = new User(userForm.getName(), userForm.getEmail(), userForm.getPhone(), userForm.getUsername(), userForm.getPassword(), userForm.getCode(), userForm.getRole());
                userService.save(user);
                Grade grade = gradeService.findById(gradeId).get();
                grade.getUser().add(user);
                gradeService.save(grade);
                ModelAndView modelAndView = new ModelAndView("ministry/create");
                modelAndView.addObject("userForm", userForm);
                modelAndView.addObject("grades", gradeService.findAll());
                modelAndView.addObject("roles", roleService.findAll());
                modelAndView.addObject("message", "Tao moi thanh cong");
                return modelAndView;
            }
        }
        ModelAndView modelAndView = new ModelAndView("ministry/create");
        modelAndView.addObject("userForm", userForm);
        modelAndView.addObject("grades", gradeService.findAll());
        modelAndView.addObject("roles", roleService.findAll());
        modelAndView.addObject("message", "Tao moi khong thanh cong");
        return modelAndView;
    }

    @PostMapping("/admin")
    public ModelAndView saveAdmin(@Validated @ModelAttribute UserForm userForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("admin/create1");
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
                User user = new User(userForm.getName(), userForm.getEmail(), userForm.getPhone(), userForm.getUsername(), userForm.getPassword(), fileName, userForm.getRole());
                userService.save(user);
                modelAndView.addObject("userForm", userForm);
                modelAndView.addObject("roles", roleService.findAll());
                modelAndView.addObject("message", "successful new creation");
                return modelAndView;
            } else {
                User use = new User(userForm.getName(), userForm.getEmail(), userForm.getPhone(), userForm.getUsername(), userForm.getPassword(), userForm.getCode(), userForm.getRole());
                userService.save(use);
                modelAndView.addObject("userForm", userForm);
                modelAndView.addObject("roles", roleService.findAll());
                modelAndView.addObject("message", "successful new creation");
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

    @PutMapping("/{id}/{newPass}/{oldPass}")
    public ResponseEntity<String> editPass(@PathVariable Long id, @PathVariable String newPass, @PathVariable String oldPass) {
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

