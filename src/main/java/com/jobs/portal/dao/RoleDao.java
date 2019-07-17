package com.jobs.portal.dao;

import org.springframework.data.repository.CrudRepository;

import com.jobs.portal.model.security.Role;

public interface RoleDao extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}