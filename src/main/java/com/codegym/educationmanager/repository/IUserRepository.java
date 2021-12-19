package com.codegym.educationmanager.repository;

import com.codegym.educationmanager.model.role.Role;
import com.codegym.educationmanager.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Iterable<User> findUserByRole(Optional<Role> role);
    Page<User> findAllByRole(Role role, Pageable pageable);
}