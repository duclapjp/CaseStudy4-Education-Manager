package com.codegym.educationmanager.repository;

import com.codegym.educationmanager.model.code.Code;
import com.codegym.educationmanager.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICodeRepository extends JpaRepository<Code,Long> {
    Code findCodeByUser(User user);
}
