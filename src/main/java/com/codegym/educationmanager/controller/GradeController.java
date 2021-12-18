package com.codegym.educationmanager.controller;

import com.codegym.educationmanager.model.grade.Grade;
import com.codegym.educationmanager.model.user.User;
import com.codegym.educationmanager.service.grade.IGradeService;
import com.codegym.educationmanager.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin("*")
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private IGradeService gradeService;
    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public ModelAndView showListGrade() {
        Iterable<Grade> grades = gradeService.findAll();
        ModelAndView modelAndView = new ModelAndView("grade/view");
        modelAndView.addObject("grades", grades);
        return modelAndView;
    }
    @GetMapping("/page")
    public ResponseEntity<Page<Grade>> findAllPage(@PageableDefault(size = 6) Pageable pageable){
        return new ResponseEntity<>(gradeService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Iterable<Grade>> findAll() {
        return new ResponseEntity<>(gradeService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Grade> findById(@PathVariable Long id){
        return new ResponseEntity<>(gradeService.findById(id).get(), HttpStatus.OK);
    }
    @GetMapping("/gradeByUser/{id}")
    public ResponseEntity<Grade> findByUser(@PathVariable Long id){
        User user = userService.findById(id).get();
        Grade grade = gradeService.findGradeByUser(user);
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Grade> save(@RequestBody Grade grade) {
        return new ResponseEntity<>(gradeService.save(grade),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Grade> delete(@PathVariable Long id) {
        gradeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}