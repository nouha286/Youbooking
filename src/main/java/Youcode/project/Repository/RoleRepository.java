package Youcode.project.Repository;


import Youcode.project.Model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByNameRole(String name);
}
