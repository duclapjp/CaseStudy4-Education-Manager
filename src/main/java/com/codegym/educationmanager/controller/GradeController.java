package com.codegym.educationmanager.controller;

import com.codegym.educationmanager.model.code.Code;
import com.codegym.educationmanager.model.grade.Grade;
import com.codegym.educationmanager.model.user.User;
import com.codegym.educationmanager.service.code.ICodeService;
import com.codegym.educationmanager.service.grade.IGradeService;
import com.codegym.educationmanager.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private IGradeService gradeService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICodeService codeService;

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
    @GetMapping("/findcodeuser/{id}")
    public ResponseEntity<List<Code>> findAllCode(@PathVariable Long id){
        User user = userService.findById(id).get();
        Grade grade = gradeService.findGradeByUser(user);
        List<Code> codes = new ArrayList<>();
        for (int i = 0; i < grade.getUser().size(); i++){
            Code code = codeService.findCodeByUser(grade.getUser().get(i));
            codes.add(code);
        }
        return new ResponseEntity<>(codes, HttpStatus.OK);
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