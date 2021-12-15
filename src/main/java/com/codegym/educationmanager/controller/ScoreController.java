package com.codegym.educationmanager.controller;

import com.codegym.educationmanager.model.score.Score;
import com.codegym.educationmanager.service.score.IScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController()
@CrossOrigin("*")
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private IScoreService scoreService;

    @GetMapping("/view")
    public ModelAndView showAllScore() {
        ModelAndView modelAndView = new ModelAndView("/score/view");
        modelAndView.addObject("score", scoreService.findAll());
        return modelAndView;
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<Score>> showListScore() {
        return new ResponseEntity<>(scoreService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Score> createScore(@RequestBody Score score) {
        return new ResponseEntity<>(scoreService.save(score), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Score> editScore(@PathVariable Long id, @RequestBody Score score) {
        Optional<Score> scores = scoreService.findById(id);
        if (!scores.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if(score.getId() == null) {
            score.setId(id);
        }
        scoreService.save(score);
        return new ResponseEntity<>(scores.get(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Score> deleteScore(@PathVariable Long id){
        Optional<Score>score1 = scoreService.findById(id);
        if (!score1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            scoreService.deleteById(id);}
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

