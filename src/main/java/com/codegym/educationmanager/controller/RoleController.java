package com.codegym.educationmanager.controller;

import com.codegym.educationmanager.model.role.Role;
import com.codegym.educationmanager.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @GetMapping("/list")
    public ModelAndView showAllRole(){
        ModelAndView modelAndView = new ModelAndView("/role/list");
        modelAndView.addObject("role",roleService.findAll());
        return modelAndView;
    }
    @GetMapping
    public ResponseEntity<Iterable<Role>>showAll(){
        return new  ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Role>createRole(@RequestBody Role role){
        return new ResponseEntity<>(roleService.save(role),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Role>editRole(@PathVariable Long id,@RequestBody Role role){
       Optional<Role> role1 = roleService.findById(id);
        if (!role1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else
            if (role.getId()==null){
                role.setId(id);
            }
            roleService.save(role);
            return new ResponseEntity<>(role1.get(),HttpStatus.OK);
    }

}
