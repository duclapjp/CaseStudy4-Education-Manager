package com.codegym.educationmanager.repository;
import com.codegym.educationmanager.model.role.Role;
import com.codegym.educationmanager.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
//   User findUserByRole(Role role);
    Iterable<User> findUserByRole(Optional<Role> role);
}
