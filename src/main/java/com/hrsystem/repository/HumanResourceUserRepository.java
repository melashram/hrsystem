package com.hrsystem.repository;

import com.hrsystem.domain.HumanResourceUser;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the HumanResourceUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HumanResourceUserRepository extends JpaRepository<HumanResourceUser, Long> {

}
