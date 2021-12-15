package com.codegym.educationmanager.service.subject;

import com.codegym.educationmanager.model.subject.Subject;
import com.codegym.educationmanager.repository.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SubjectService implements ISubjectService{
    @Autowired
    private ISubjectRepository subjectRepository;
    @Override
    public Iterable<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> findById(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void deleteById(Long id) {
        subjectRepository.deleteById(id);
    }
}
