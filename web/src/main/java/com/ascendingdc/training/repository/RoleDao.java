package com.ascendingdc.training.repository;

import com.ascendingdc.training.model.Role;

import java.util.List;

public interface RoleDao {
    public abstract Role save(Role role);
    public abstract List<Role> getRoles();
    public abstract Role getById(Long id);
    public abstract int deleteById(Role role);
    public abstract int update(Role role);
    public abstract Role getRoleByName(String roleName);
}
