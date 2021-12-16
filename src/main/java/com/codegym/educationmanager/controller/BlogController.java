package com.codegym.educationmanager.controller;

import com.codegym.educationmanager.model.blog.Blog;
import com.codegym.educationmanager.service.blog.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @GetMapping("/list")
    public ModelAndView showAllBlog() {
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blog", blogService.findAll());
        return modelAndView;
    }

    @GetMapping("/view")
    public ResponseEntity<Iterable<Blog>> showAll() {
        return new ResponseEntity<>(blogService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        return new ResponseEntity<>(blogService.save(blog), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> editBlog(@PathVariable Long id, @RequestBody Blog blog) {
        Optional<Blog> blogs = blogService.findById(id);
        if (!blogs.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (blog.getId() == null) {
            blog.setId(id);
        }
        blogService.save(blog);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (!blog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else
            blogService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
