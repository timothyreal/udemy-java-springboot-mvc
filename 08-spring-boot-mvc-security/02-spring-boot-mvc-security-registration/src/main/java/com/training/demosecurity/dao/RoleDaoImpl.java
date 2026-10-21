package com.training.demosecurity.dao;

import org.springframework.stereotype.Repository;

import com.training.demosecurity.entity.Role;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository 
public class RoleDaoImpl implements RoleDao {
    private final EntityManager _entityManager;

    public RoleDaoImpl(EntityManager entityManager) {
        this._entityManager = entityManager;
    }

    @Override
    public Role findRoleByName(String roleName) {
        Role role = null;
        TypedQuery<Role> query = this._entityManager.createQuery("FROM Role WHERE name=:roleName", Role.class);
        query.setParameter("roleName", roleName);

        try {
            role = query.getSingleResult();
        } catch (Exception e) {
            role = null;
        }
        
        return role;
    }

    
}
