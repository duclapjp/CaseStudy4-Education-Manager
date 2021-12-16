package com.codegym.educationmanager.service.grade;

import com.codegym.educationmanager.model.grade.Grade;
import com.codegym.educationmanager.model.user.User;
import com.codegym.educationmanager.service.IGeneralService;

public interface IGradeService extends IGeneralService<Grade> {
    Grade findGradeByUser(User user);
}
