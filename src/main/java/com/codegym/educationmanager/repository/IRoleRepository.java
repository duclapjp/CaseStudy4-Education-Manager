package com.codegym.educationmanager.repository;

import com.codegym.educationmanager.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
<<<<<<< HEAD
}
=======
    Role findByName(String name);
}
>>>>>>> 210e4a86f174a8cb99127d3bc31d5e77cadab46c
