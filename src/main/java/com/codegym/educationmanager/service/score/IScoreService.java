package com.codegym.educationmanager.service.score;

import com.codegym.educationmanager.model.score.Score;
import com.codegym.educationmanager.model.user.User;
import com.codegym.educationmanager.service.IGeneralService;

public interface IScoreService extends IGeneralService<Score> {
    Iterable<Score> findAllByUser(User user);
}
