package com.codegym.educationmanager.repository;

import com.codegym.educationmanager.model.score.Score;
import com.codegym.educationmanager.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IScoreRepository extends JpaRepository<Score, Long> {
    Iterable<Score> findAllByUser(User user);
}
