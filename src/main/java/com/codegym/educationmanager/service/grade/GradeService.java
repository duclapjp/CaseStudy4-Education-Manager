package com.codegym.educationmanager.service.grade;

import com.codegym.educationmanager.model.grade.Grade;
import com.codegym.educationmanager.model.user.User;
import com.codegym.educationmanager.repository.IGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Grade save(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public void deleteById(Long id) {
        gradeRepository.deleteById(id);
    }

    @Override
    public Grade findGradeByUser(User user) {
        return gradeRepository.findGradeByUser(user);
    }

    @Override
    public Page<Grade> findAll(Pageable pageable) {
        return gradeRepository.findAll(pageable);
    }
}
