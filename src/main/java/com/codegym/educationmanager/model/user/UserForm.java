package com.codegym.educationmanager.model.user;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

public class UserForm {
    private Long id;
    @NotEmpty(message = "khong de trong")
    private String name;
    @NotEmpty(message = "khong de trong")
    private String email;
    @NotEmpty(message = "khong de trong")
    private String phone;
    @NotEmpty(message = "khong de trong")
    private String username;
    @NotEmpty(message = "khong de trong")
    private String password;
    private MultipartFile image;

    public UserForm() {
    }

    public UserForm(String name, String email, String phone, String username, String password, MultipartFile image) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.image = image;
    }

    public UserForm(Long id, String name, String email, String phone, String username, String password, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.image = image;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
