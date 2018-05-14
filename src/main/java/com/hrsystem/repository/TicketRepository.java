package com.hrsystem.repository;

import com.hrsystem.domain.Ticket;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Ticket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("select ticket from Ticket ticket where ticket.user.login = ?#{principal.username}")
    List<Ticket> findByUserIsCurrentUser();

    @Query("select ticket from Ticket ticket where ticket.request.department.name like :department")
    List<Ticket> findByRequestDepartment(@Param("department") String department);

    //Search Queries

    @Query("select ticket from Ticket ticket where ticket.user.login like :name " +
        "and ticket.request.department.name like :department ")
    List<Ticket> findByUserSearch(@Param("name") String name, @Param("department") String department);

    @Query("select ticket from Ticket ticket where ticket.ticketStatus.ticketStatus like :ticketStatus " +
        "and ticket.request.department.name like :department")
    List<Ticket> findByTicketStatusSearch(@Param("ticketStatus") String ticketStatus,
                                          @Param("department") String department);

    @Query("select ticket from Ticket ticket where ticket.request.type like :requestType " +
        "and ticket.request.department.name like :department")
    List<Ticket>findByRequestTypeSearch(@Param("requestType")String requestType ,
                                        @Param("department") String department);


    @Query("select ticket from Ticket ticket where ticket.user.login like :name " +
        "and ticket.ticketStatus.ticketStatus like :ticketStatus " +
        "and ticket.request.type like :requestType " +
        "and ticket.request.department.name like :department")
     List<Ticket>findByGeneralSearch(@Param ("name") String name,
                                    @Param("ticketStatus") String ticketStatus,
                                    @Param("requestType") String requestType,
                                    @Param("department") String department );

}
