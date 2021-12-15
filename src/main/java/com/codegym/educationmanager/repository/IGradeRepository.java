package com.codegym.educationmanager.repository;

import com.codegym.educationmanager.model.grade.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGradeRepository extends JpaRepository<Grade, Long> {
}
