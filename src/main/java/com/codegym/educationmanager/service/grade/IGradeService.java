package com.codegym.educationmanager.service.grade;

import com.codegym.educationmanager.model.grade.Grade;
import com.codegym.educationmanager.model.user.User;
import com.codegym.educationmanager.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGradeService extends IGeneralService<Grade> {
    Grade findGradeByUser(User user);
    Page<Grade> findAll(Pageable pageable);
}
