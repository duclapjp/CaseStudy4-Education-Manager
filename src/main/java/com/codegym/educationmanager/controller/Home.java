package com.codegym.educationmanager.controller;

import com.codegym.educationmanager.model.jwt.JwtResponse;
import com.codegym.educationmanager.model.user.User;
import com.codegym.educationmanager.service.jwt.JwtService;
import com.codegym.educationmanager.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/home")
public class Home {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;
    @GetMapping("/test")
    public String returnString(){
        return "redirect:http://localhost:63342/web/contact.html";
    }
    @PostMapping
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(user.getUsername()).get();
        return new ResponseEntity<>(new JwtResponse(currentUser.getId(), token, currentUser.getName(),currentUser.getEmail(), currentUser.getPhone(), userDetails.getUsername(), currentUser.getImage(), userDetails.getAuthorities()), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }
}