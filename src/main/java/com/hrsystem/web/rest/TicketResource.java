package com.hrsystem.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hrsystem.domain.Ticket;

import com.hrsystem.domain.TicketStatus;
import com.hrsystem.domain.User;
import com.hrsystem.repository.TicketRepository;
import com.hrsystem.repository.TicketStatusRepository;
import com.hrsystem.repository.UserRepository;
import com.hrsystem.security.SecurityUtils;
import com.hrsystem.service.MailService;
import com.hrsystem.web.rest.errors.BadRequestAlertException;
import com.hrsystem.web.rest.errors.InternalServerErrorException;
import com.hrsystem.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * REST controller for managing Ticket.
 */
@RestController
@RequestMapping("/api")
public class TicketResource {

    private final Logger log = LoggerFactory.getLogger(TicketResource.class);

    private static final String ENTITY_NAME = "ticket";

    private final TicketRepository ticketRepository;

    @Autowired
    private MailService  mailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketStatusRepository ticketStatusRepository;

    private String [] RequestTypes ={"HR" , "IT"};

    public TicketResource(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    /**
     * POST  /tickets : Create a new ticket.
     *
     * @param ticket the ticket to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ticket, or with status 400 (Bad Request) if the ticket has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tickets")
    @Timed
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) throws URISyntaxException {
        log.debug("REST request to save Ticket : {}", ticket);
        if (ticket.getId() != null) {
            throw new BadRequestAlertException("A new ticket cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ticket.setCreationDate(Instant.now());
        TicketStatus ticketStatus = ticketStatusRepository.getPendingStatus();
        ticket.setTicketStatus(ticketStatus);

        final String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
        Optional<User> currentLoggedInUser = userRepository.findOneByLogin(userLogin);

        User emptyUser = new User();
        User userLoggedIn = currentLoggedInUser.orElse(emptyUser);
        userLoggedIn.setAuthorities(null);

        ticket.setUser(userLoggedIn);

        Ticket result = ticketRepository.save(ticket);

        String resultTicketType = result.getRequest().getDepartment().getName();

        //HR
        if(resultTicketType.equals(RequestTypes[0])){
            mailService.sendRequestMailFromDepartment(userLoggedIn , result , resultTicketType );
        }
        //IT
        else if(resultTicketType.equals(RequestTypes[1]) ){
            mailService.sendRequestMailFromDepartment(userLoggedIn , result ,resultTicketType);
        }
        return ResponseEntity.created(new URI("/api/tickets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tickets : Updates an existing ticket.
     *
     * @param ticket the ticket to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ticket,
     * or with status 400 (Bad Request) if the ticket is not valid,
     * or with status 500 (Internal Server Error) if the ticket couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tickets")
    @Timed
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket) throws URISyntaxException {
        log.debug("REST request to update Ticket : {}", ticket);
        if (ticket.getId() == null) {

            return createTicket(ticket);

        }
        Ticket result = ticketRepository.save(ticket);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ticket.getId().toString()))
            .body(result);
    }

    @PutMapping("/tickets/hrit")
    @Timed
    public ResponseEntity<Ticket> assignTicketHRIT(@RequestBody Ticket ticket
                                                   ) throws URISyntaxException {
        log.debug("REST request to assign Ticket : {}", ticket);

        ticket.setAcceptanceDate(Instant.now());
        final String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
        Optional<User> currentLoggedInUser = userRepository.findOneByLogin(userLogin);

        User emptyUser = new User();
        User userLoggedIn = currentLoggedInUser.orElse(emptyUser);
        userLoggedIn.setAuthorities(null);

        ticket.setAssignedUser(userLoggedIn);

        Ticket result = ticketRepository.save(ticket);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ticket.getId().toString()))
            .body(result);
    }

    @PutMapping("/tickets/reassignTicket")
    @Timed
    public ResponseEntity<Ticket> reassignTest(@RequestBody Ticket ticket
    ) throws URISyntaxException {
        log.debug("REST request to Reassign Ticket : {}", ticket);

        Ticket result = ticketRepository.save(ticket);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ticket.getId().toString()))
            .body(result);
    }

    @PutMapping("/tickets/reassignTicketToOwner")
    @Timed
    public ResponseEntity<Ticket> reassignTicketHRIT(@RequestBody Ticket ticket) throws URISyntaxException {
        log.debug("REST request to reassign Ticket : {}", ticket);

        ticket.setAssignedUser(ticket.getUser());

        Ticket result = ticketRepository.save(ticket);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ticket.getId().toString()))
            .body(result);
    }
    /**
     * GET  /tickets : get all the tickets.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tickets in body
     */
    @GetMapping("/tickets")
    @Timed
    public List<Ticket> getAllTickets() {
        log.debug("REST request to get all Tickets");
        List<Ticket>tickets = ticketRepository.findAll();
        return ticketRepository.findAll();
        }

    /**
     * GET  /tickets/:id : get the "id" ticket.
     *
     * @param id the id of the ticket to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ticket, or with status 404 (Not Found)
     */
    @GetMapping("/tickets/{id}")
    @Timed
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
        log.debug("REST request to get Ticket : {}", id);
        Ticket ticket = ticketRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ticket));
    }

    @GetMapping("/tickets/usertickets")
    @Timed
    public List<Ticket> getTicketLoggedInUser() {
        log.debug("REST request to get User Tickets : {}");
        List<Ticket> tickets = ticketRepository.findByUserIsCurrentUser();
        return ticketRepository.findByUserIsCurrentUser();
    }

    @GetMapping("/tickets/hrtickets")
    @Timed
    public List<Ticket> getAllHRTickets() {
        log.debug("REST request to get all HR Tickets");
        List<Ticket>Hrtickets = ticketRepository.findByRequestDepartment("HR");
        return ticketRepository.findByRequestDepartment("HR");
    }

    @GetMapping("/tickets/ittickets")
    @Timed
    public List<Ticket> getAllITTickets() {
        log.debug("REST request to get all IT Tickets");
        List<Ticket>Ittickets = ticketRepository.findByRequestDepartment("IT");
        return ticketRepository.findByRequestDepartment("IT");
    }

    @GetMapping("/tickets/searchNameIt")
    @ResponseBody
    public  List<Ticket> SearchTicketsByNameIt(
        @RequestParam(value = "searchToken" , required = false) String  searchToken ,
        @RequestParam(value = "searchTicketStatus" , required = false) String searchTicketStatus,
        @RequestParam(value = "searchRequestType" , required = false) String searchRequestType) {
        List<Ticket> searchTestGeneral = ticketRepository.findByGeneralSearch(searchToken , searchTicketStatus ,searchRequestType , "IT" );
        return searchTestGeneral;
    }

    @GetMapping("/tickets/searchNameHr")
    @ResponseBody
    public  List<Ticket> SearchTicketsByNameHr(
        @RequestParam(value = "searchToken" , required = false) String  searchToken ,
        @RequestParam(value = "searchTicketStatus" , required = false) String searchTicketStatus,
        @RequestParam(value = "searchRequestType" , required = false) String searchRequestType) {
        List<Ticket> searchTestGeneral = ticketRepository.findByGeneralSearch(searchToken , searchTicketStatus , searchRequestType, "HR" );
        return searchTestGeneral;
    }

    /**
     * DELETE  /tickets/:id : delete the "id" ticket.
     *
     * @param id the id of the ticket to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tickets/{id}")
    @Timed
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        log.debug("REST request to delete Ticket : {}", id);
        ticketRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }


}
