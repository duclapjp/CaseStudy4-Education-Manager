package com.codegym.educationmanager.service.user;

import com.codegym.educationmanager.model.user.User;
import com.codegym.educationmanager.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    Optional<User> findByUsername(String username);
    Page<User> pageUser(Pageable pageable);
}
