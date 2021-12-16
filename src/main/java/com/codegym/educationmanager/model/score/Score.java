package com.codegym.educationmanager.model.score;

import com.codegym.educationmanager.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double math;
    private Double literature;
    private Double physics;
    @ManyToOne(targetEntity = User.class)
    private User user;

    public Score() {
    }

    public Score(Double math, Double literature, Double physics, User user) {
        this.math = math;
        this.literature = literature;
        this.physics = physics;
        this.user = user;
    }

    public Score(Long id, Double math, Double literature, Double physics, User user) {
        this.id = id;
        this.math = math;
        this.literature = literature;
        this.physics = physics;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMath() {
        return math;
    }

    public void setMath(Double math) {
        this.math = math;
    }

    public Double getLiterature() {
        return literature;
    }

    public void setLiterature(Double literature) {
        this.literature = literature;
    }

    public Double getPhysics() {
        return physics;
    }

    public void setPhysics(Double physics) {
        this.physics = physics;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
