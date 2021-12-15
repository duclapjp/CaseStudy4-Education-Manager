package com.codegym.educationmanager.repository;

import com.codegym.educationmanager.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
