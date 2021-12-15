package com.codegym.educationmanager.model.score;

import com.codegym.educationmanager.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double score;
    @ManyToOne(targetEntity = User.class)
    private User user;

    public Score() {
    }
    public Score(Double score, User student) {
        this.score = score;
        this.user = student;
    }

    public Score(Long id, Double score, User student) {
        this.id = id;
        this.score = score;
        this.user = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public User getStudent() {
        return user;
    }

    public void setStudent(User student) {
        this.user = student;
    }
}
