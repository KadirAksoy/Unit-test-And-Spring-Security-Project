package com.kadiraksoy.UnittestAndSpringSecurityProject.repository;

import com.kadiraksoy.UnittestAndSpringSecurityProject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByName(String name);
}
