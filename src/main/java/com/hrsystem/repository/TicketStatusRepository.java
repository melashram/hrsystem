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

    @Query("select ticketStatus from TicketStatus ticketStatus where ticketStatus.ticketStatus like 'Pending'")
    public TicketStatus getPendingStatus();

}
