package com.codegym.educationmanager.service.role;

import com.codegym.educationmanager.model.role.Role;
<<<<<<< HEAD
import com.codegym.educationmanager.repository.IRoleRepository;
import com.codegym.educationmanager.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
}
=======
import com.codegym.educationmanager.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}
>>>>>>> 210e4a86f174a8cb99127d3bc31d5e77cadab46c
