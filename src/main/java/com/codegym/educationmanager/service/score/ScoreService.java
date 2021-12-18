package com.codegym.educationmanager.service.score;

import com.codegym.educationmanager.model.score.Score;
import com.codegym.educationmanager.model.user.User;
import com.codegym.educationmanager.repository.IScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ScoreService implements IScoreService{
    @Autowired
    private IScoreRepository scoreRepository;
    @Override
    public Iterable<Score> findAll() {
        return scoreRepository.findAll();
    }

    @Override
    public Optional<Score> findById(Long id) {
        return scoreRepository.findById(id);
    }

    @Override
    public Score save(Score score) {
        return scoreRepository.save(score);
    }

    @Override
    public void deleteById(Long id) {
        scoreRepository.deleteById(id);
    }

    @Override
    public Iterable<Score> findAllByUser(User user) {
        return scoreRepository.findAllByUser(user);
    }
}
