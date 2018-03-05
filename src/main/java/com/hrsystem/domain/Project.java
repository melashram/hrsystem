package com.hrsystem.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Project.
 */
@Entity
@Table(name = "project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "description")
    private String description;

    @Column(name = "project_address")
    private String projectAddress;

    @Column(name = "project_company")
    private String projectCompany;

    @Column(name = "project_city")
    private String projectCity;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public Project projectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public Project description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public Project projectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
        return this;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getProjectCompany() {
        return projectCompany;
    }

    public Project projectCompany(String projectCompany) {
        this.projectCompany = projectCompany;
        return this;
    }

    public void setProjectCompany(String projectCompany) {
        this.projectCompany = projectCompany;
    }

    public String getProjectCity() {
        return projectCity;
    }

    public Project projectCity(String projectCity) {
        this.projectCity = projectCity;
        return this;
    }

    public void setProjectCity(String projectCity) {
        this.projectCity = projectCity;
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
        Project project = (Project) o;
        if (project.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), project.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Project{" +
            "id=" + getId() +
            ", projectName='" + getProjectName() + "'" +
            ", description='" + getDescription() + "'" +
            ", projectAddress='" + getProjectAddress() + "'" +
            ", projectCompany='" + getProjectCompany() + "'" +
            ", projectCity='" + getProjectCity() + "'" +
            "}";
    }
}
