package com.codegym.educationmanager.controller;

import com.codegym.educationmanager.model.grade.Grade;
import com.codegym.educationmanager.service.grade.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/list")
    public ModelAndView showListGrade() {
        Iterable<Grade> grades = gradeService.findAll();
        ModelAndView modelAndView = new ModelAndView("grade/view");
        modelAndView.addObject("grades", grades);
        return modelAndView;
    }
    @GetMapping
    public ResponseEntity<Iterable<Grade>> findAll() {
        return new ResponseEntity<>(gradeService.findAll(), HttpStatus.OK);
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