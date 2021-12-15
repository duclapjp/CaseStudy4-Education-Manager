package com.codegym.educationmanager.repository;

import com.codegym.educationmanager.model.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubjectRepository extends JpaRepository<Subject, Long> {
}
