package com.kadiraksoy.UnittestAndSpringSecurityProject.repository;

import com.kadiraksoy.UnittestAndSpringSecurityProject.model.Misket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MisketRepository extends JpaRepository<Misket,Integer> {

    Optional<Misket> findByType(String type);
}
