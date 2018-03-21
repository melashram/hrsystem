package com.hrsystem.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

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

    @Column(name = "to_whom")
    private String toWhom;

    @Column(name = "jhi_comment")
    private String comment;

    @Column(name = "creationdate")
    private Instant creationdate;

    @Column(name = "acceptance_date")
    private LocalDate acceptanceDate;

    @Column(name = "ticket_status")
    private String ticketStatus;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(unique = true)
    private HumanResourceUser assigendTo;

    @OneToOne
    @JoinColumn(unique = true)
    private Request ticketRequst;

    @ManyToOne(optional = false)
    @NotNull
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

    public String getToWhom() {
        return toWhom;
    }

    public Ticket toWhom(String toWhom) {
        this.toWhom = toWhom;
        return this;
    }

    public void setToWhom(String toWhom) {
        this.toWhom = toWhom;
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

    public Instant getCreationdate() {
        return creationdate;
    }

    public Ticket creationdate(Instant creationdate) {
        this.creationdate = creationdate;
        return this;
    }

    public void setCreationdate(Instant creationdate) {
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

    public String getTicketStatus() {
        return ticketStatus;
    }

    public Ticket ticketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
        return this;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
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

    public HumanResourceUser getAssigendTo() {
        return assigendTo;
    }

    public Ticket assigendTo(HumanResourceUser humanResourceUser) {
        this.assigendTo = humanResourceUser;
        return this;
    }

    public void setAssigendTo(HumanResourceUser humanResourceUser) {
        this.assigendTo = humanResourceUser;
    }

    public Request getTicketRequst() {
        return ticketRequst;
    }

    public Ticket ticketRequst(Request request) {
        this.ticketRequst = request;
        return this;
    }

    public void setTicketRequst(Request request) {
        this.ticketRequst = request;
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
            ", toWhom='" + getToWhom() + "'" +
            ", comment='" + getComment() + "'" +
            ", creationdate='" + getCreationdate() + "'" +
            ", acceptanceDate='" + getAcceptanceDate() + "'" +
            ", ticketStatus='" + getTicketStatus() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
