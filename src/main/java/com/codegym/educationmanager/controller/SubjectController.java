package com.codegym.educationmanager.controller;

import com.codegym.educationmanager.model.grade.Grade;
import com.codegym.educationmanager.model.subject.Subject;
import com.codegym.educationmanager.repository.ISubjectRepository;
import com.codegym.educationmanager.service.grade.IGradeService;
import com.codegym.educationmanager.service.subject.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    private ISubjectService subjectService;

    //show list
    @GetMapping("/list")
    public ModelAndView showListGrade() {
        Iterable<Subject> subjects = subjectService.findAll();
        ModelAndView modelAndView = new ModelAndView("subject/view");
        modelAndView.addObject("subject", subjects);
        return modelAndView;
    }

    @GetMapping
    public ResponseEntity<Iterable<Subject>> findAll() {
        return new ResponseEntity<>(subjectService.findAll(), HttpStatus.OK);
    }
    //them
    @PostMapping
    public ResponseEntity<Subject> create(@RequestBody Subject subject) {
        return new ResponseEntity<>(subjectService.save(subject), HttpStatus.CREATED);
    }

    //    sua
    @PutMapping("/{id}")
    public ResponseEntity<Subject> update(@PathVariable Long id, @RequestBody Subject subject) {
        Optional<Subject> subjectOptional = subjectService.findById(id);
        if (!subjectOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (subject.getId() == null) {
            subject.setId(id);
        }
        subjectService.save(subject);
        return new ResponseEntity<>(subjectOptional.get(), HttpStatus.OK);
    }

    //xoa

    @DeleteMapping("/{id}")
    public ResponseEntity<Subject> delete(@PathVariable Long id) {
        Optional<Subject> subjectOptional = subjectService.findById(id);
        if (!subjectOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            subjectService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
