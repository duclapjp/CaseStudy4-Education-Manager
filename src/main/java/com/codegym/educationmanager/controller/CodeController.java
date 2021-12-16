package com.codegym.educationmanager.controller;

import com.codegym.educationmanager.model.code.Code;
import com.codegym.educationmanager.model.subject.Subject;
import com.codegym.educationmanager.repository.ISubjectRepository;
import com.codegym.educationmanager.service.code.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/codes")
public class CodeController {
    @Autowired
    private ICodeService codeService;

    //show list
    @GetMapping("/list")
    public ModelAndView showListGrade() {
        Iterable<Code> subjects = codeService.findAll();
        ModelAndView modelAndView = new ModelAndView("code/view");
        modelAndView.addObject("subject", subjects);
        return modelAndView;
    }

    @GetMapping
    public ResponseEntity<Iterable<Code>> findAll() {
        return new ResponseEntity<>(codeService.findAll(), HttpStatus.OK);
    }
    //them
    @PostMapping
    public ResponseEntity<Code> create(@RequestBody Code code) {
        return new ResponseEntity<>(codeService.save(code), HttpStatus.CREATED);
    }

    //    sua
    @PutMapping("/{id}")
    public ResponseEntity<Code> update(@PathVariable Long id, @RequestBody Code code) {
        Optional<Code> codeOptional = codeService.findById(id);
        if (!codeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (code.getId() == null) {
            code.setId(id);
        }
        codeService.save(code);
        return new ResponseEntity<>(codeOptional.get(), HttpStatus.OK);
    }

    //xoa

    @DeleteMapping("/{id}")
    public ResponseEntity<Code> delete(@PathVariable Long id) {
        Optional<Code> codeOptional = codeService.findById(id);
        if (!codeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            codeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
