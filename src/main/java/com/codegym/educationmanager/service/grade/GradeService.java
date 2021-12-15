package com.codegym.educationmanager.service.grade;

import com.codegym.educationmanager.model.grade.Grade;
import com.codegym.educationmanager.repository.IGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GradeService implements IGradeService{
    @Autowired
    private IGradeRepository gradeRepository;
    @Override
    public Iterable<Grade> findAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Optional<Grade> findById(Long id) {
        return gradeRepository.findById(id);
    }

    @Override
    public void save(Grade grade) {
        gradeRepository.save(grade);
    }

    @Override
    public void deleteById(Long id) {
        gradeRepository.deleteById(id);
    }
}
