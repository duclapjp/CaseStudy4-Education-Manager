package com.codegym.educationmanager.service.role;

import com.codegym.educationmanager.model.role.Role;
import com.codegym.educationmanager.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}