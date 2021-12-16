package com.codegym.educationmanager.service.role;

import com.codegym.educationmanager.model.role.Role;
import com.codegym.educationmanager.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
<<<<<<< HEAD
@Service
public class RoleService implements IRoleService{
    @Autowired
    private IRoleRepository roleRepository;
=======

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

>>>>>>> 210e4a86f174a8cb99127d3bc31d5e77cadab46c
    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
<<<<<<< HEAD
         roleRepository.deleteById(id);
    }
}
=======
        roleRepository.deleteById(id);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
>>>>>>> 210e4a86f174a8cb99127d3bc31d5e77cadab46c
