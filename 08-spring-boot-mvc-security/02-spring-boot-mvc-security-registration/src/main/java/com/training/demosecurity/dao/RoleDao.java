package com.training.demosecurity.dao;

import com.training.demosecurity.entity.Role;

public interface RoleDao {
    public Role findRoleByName(String roleName);
}
