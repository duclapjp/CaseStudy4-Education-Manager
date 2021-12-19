package com.codegym.educationmanager.repository;

import com.codegym.educationmanager.model.grade.Grade;
import com.codegym.educationmanager.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IGradeRepository extends JpaRepository<Grade, Long> {
    Grade findGradeByUser(User user);
}
