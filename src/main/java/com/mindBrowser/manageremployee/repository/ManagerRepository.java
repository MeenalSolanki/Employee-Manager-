package com.mindBrowser.manageremployee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mindBrowser.manageremployee.entity.Manager;

@RepositoryRestResource
public interface ManagerRepository extends JpaRepository<Manager, Long>
{

	Optional<Manager> findByEmail(String email);

	Boolean existsByEmail(String email);
}
