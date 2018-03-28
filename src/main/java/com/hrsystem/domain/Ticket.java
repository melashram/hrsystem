package com.hrsystem.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Ticket.
 */
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reason")
    private String reason;

    @Column(name = "jhi_comment")
    private String comment;

    @Column(name = "creationdate")
    private LocalDate creationdate;

    @Column(name = "acceptance_date")
    private LocalDate acceptanceDate;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(unique = true)
    private Request request;

    @OneToOne
    @JoinColumn(unique = true)
    private TicketStatus ticketStatus;

    @OneToOne
    @JoinColumn(unique = true)
    private HumanResourceUser assignedTo;

    @ManyToOne
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public Ticket reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getComment() {
        return comment;
    }

    public Ticket comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getCreationdate() {
        return creationdate;
    }

    public Ticket creationdate(LocalDate creationdate) {
        this.creationdate = creationdate;
        return this;
    }

    public void setCreationdate(LocalDate creationdate) {
        this.creationdate = creationdate;
    }

    public LocalDate getAcceptanceDate() {
        return acceptanceDate;
    }

    public Ticket acceptanceDate(LocalDate acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
        return this;
    }

    public void setAcceptanceDate(LocalDate acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public String getDescription() {
        return description;
    }

    public Ticket description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Request getRequest() {
        return request;
    }

    public Ticket request(Request request) {
        this.request = request;
        return this;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public Ticket ticketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
        return this;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public HumanResourceUser getAssignedTo() {
        return assignedTo;
    }

    public Ticket assignedTo(HumanResourceUser humanResourceUser) {
        this.assignedTo = humanResourceUser;
        return this;
    }

    public void setAssignedTo(HumanResourceUser humanResourceUser) {
        this.assignedTo = humanResourceUser;
    }

    public User getUser() {
        return user;
    }

    public Ticket user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        if (ticket.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ticket.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ticket{" +
            "id=" + getId() +
            ", reason='" + getReason() + "'" +
            ", comment='" + getComment() + "'" +
            ", creationdate='" + getCreationdate() + "'" +
            ", acceptanceDate='" + getAcceptanceDate() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
