package com.codegym.educationmanager.model.code;

import com.codegym.educationmanager.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "code")
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(targetEntity = User.class)
    private User user;

    public Code() {
    }

    public Code(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Code(Long id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
