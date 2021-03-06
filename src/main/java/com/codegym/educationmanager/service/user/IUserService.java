package com.codegym.educationmanager.service.user;

import com.codegym.educationmanager.model.role.Role;
import com.codegym.educationmanager.model.user.User;
import com.codegym.educationmanager.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    Optional<User> findByUsername(String username);

    Optional<User> findByCode(String code);

    Page<User> pageUser(Pageable pageable);

    Iterable<User> findUserByRole(Optional<Role> role);

    Page<User> findAllByRole(Role role, Pageable pageable);

    Iterable<User> findAllByRole(Optional<Role> role);

}
