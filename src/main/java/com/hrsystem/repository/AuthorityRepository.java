package com.hrsystem.repository;

import com.hrsystem.domain.Authority;

import org.springframework.boot.actuate.autoconfigure.ShellProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    @Query("select authority from Authority authority where name like 'ROLE_EMPLOYEE'")
    Set<Authority> findRoleEmployee();
}
