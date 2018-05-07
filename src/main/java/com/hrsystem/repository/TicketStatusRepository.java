package com.hrsystem.repository;

import com.hrsystem.domain.TicketStatus;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TicketStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TicketStatusRepository extends JpaRepository<TicketStatus, Long> {



}
