package com.hrsystem.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "personal_phone_number")
    private String personalPhoneNumber;

    @Column(name = "work_phone_number")
    private String workPhoneNumber;

    @Column(name = "d_ob")
    private LocalDate dOB;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "title")
    private String title;

    @Column(name = "social_insurance_number")
    private String socialInsuranceNumber;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "national_id_number")
    private String nationalIdNumber;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "cib_acount_number")
    private String cibAcountNumber;

    @Column(name = "city_country")
    private String cityCountry;

    @Column(name = "home_address")
    private String homeAddress;

    @OneToOne
    @MapsId
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonalPhoneNumber() {
        return personalPhoneNumber;
    }

    public Employee personalPhoneNumber(String personalPhoneNumber) {
        this.personalPhoneNumber = personalPhoneNumber;
        return this;
    }

    public void setPersonalPhoneNumber(String personalPhoneNumber) {
        this.personalPhoneNumber = personalPhoneNumber;
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    public Employee workPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
        return this;
    }

    public void setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }

    public LocalDate getdOB() {
        return dOB;
    }

    public Employee dOB(LocalDate dOB) {
        this.dOB = dOB;
        return this;
    }

    public void setdOB(LocalDate dOB) {
        this.dOB = dOB;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public Employee hireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getTitle() {
        return title;
    }

    public Employee title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSocialInsuranceNumber() {
        return socialInsuranceNumber;
    }

    public Employee socialInsuranceNumber(String socialInsuranceNumber) {
        this.socialInsuranceNumber = socialInsuranceNumber;
        return this;
    }

    public void setSocialInsuranceNumber(String socialInsuranceNumber) {
        this.socialInsuranceNumber = socialInsuranceNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public Employee nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationalIdNumber() {
        return nationalIdNumber;
    }

    public Employee nationalIdNumber(String nationalIdNumber) {
        this.nationalIdNumber = nationalIdNumber;
        return this;
    }

    public void setNationalIdNumber(String nationalIdNumber) {
        this.nationalIdNumber = nationalIdNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public Employee passportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
        return this;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getCibAcountNumber() {
        return cibAcountNumber;
    }

    public Employee cibAcountNumber(String cibAcountNumber) {
        this.cibAcountNumber = cibAcountNumber;
        return this;
    }

    public void setCibAcountNumber(String cibAcountNumber) {
        this.cibAcountNumber = cibAcountNumber;
    }

    public String getCityCountry() {
        return cityCountry;
    }

    public Employee cityCountry(String cityCountry) {
        this.cityCountry = cityCountry;
        return this;
    }

    public void setCityCountry(String cityCountry) {
        this.cityCountry = cityCountry;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public Employee homeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public User getUser() {
        return user;
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
        Employee employee = (Employee) o;
        if (employee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", personalPhoneNumber='" + getPersonalPhoneNumber() + "'" +
            ", workPhoneNumber='" + getWorkPhoneNumber() + "'" +
            ", dOB='" + getdOB() + "'" +
            ", hireDate='" + getHireDate() + "'" +
            ", title='" + getTitle() + "'" +
            ", socialInsuranceNumber='" + getSocialInsuranceNumber() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", nationalIdNumber='" + getNationalIdNumber() + "'" +
            ", passportNumber='" + getPassportNumber() + "'" +
            ", cibAcountNumber='" + getCibAcountNumber() + "'" +
            ", cityCountry='" + getCityCountry() + "'" +
            ", homeAddress='" + getHomeAddress() + "'" +
            "}";
    }
}
