package com.codegym.educationmanager.model.grade;
import com.codegym.educationmanager.model.blog.Blog;
import com.codegym.educationmanager.model.user.User;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(targetEntity = User.class)
    private List<User> user;
    @OneToOne(targetEntity = Blog.class)
    private Blog blog;

    public Grade() {
    }

    public Grade(String name, List<User> users, Blog blog) {
        this.name = name;
        this.user = users;
        this.blog = blog;
    }

    public Grade(String name) {
        this.name = name;
    }

    public Grade(Long id, String name, List<User> users, Blog blog) {
        this.id = id;
        this.name = name;
        this.user = users;
        this.blog = blog;
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

    public List<User> getUsers() {
        return user;
    }

    public void setUsers(List<User> users) {
        this.user = users;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
