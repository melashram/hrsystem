package com.hrsystem.web.rest;

import com.hrsystem.HrsystemApp;

import com.hrsystem.domain.Employee;
import com.hrsystem.domain.User;
import com.hrsystem.repository.EmployeeRepository;
import com.hrsystem.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.hrsystem.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EmployeeResource REST controller.
 *
 * @see EmployeeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HrsystemApp.class)
public class EmployeeResourceIntTest {

    private static final String DEFAULT_PERSONAL_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PERSONAL_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_WORK_PHONE_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_D_OB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_D_OB = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_HIRE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_HIRE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_SOCIAL_INSURANCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SOCIAL_INSURANCE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONALITY = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITY = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_NATIONAL_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PASSPORT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PASSPORT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CIB_ACOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CIB_ACOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CITY_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_CITY_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_HOME_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_HOME_ADDRESS = "BBBBBBBBBB";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEmployeeMockMvc;

    private Employee employee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmployeeResource employeeResource = new EmployeeResource(employeeRepository);
        this.restEmployeeMockMvc = MockMvcBuilders.standaloneSetup(employeeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Employee createEntity(EntityManager em) {
        Employee employee = new Employee()
            .personalPhoneNumber(DEFAULT_PERSONAL_PHONE_NUMBER)
            .workPhoneNumber(DEFAULT_WORK_PHONE_NUMBER)
            .dOB(DEFAULT_D_OB)
            .hireDate(DEFAULT_HIRE_DATE)
            .title(DEFAULT_TITLE)
            .socialInsuranceNumber(DEFAULT_SOCIAL_INSURANCE_NUMBER)
            .nationality(DEFAULT_NATIONALITY)
            .nationalIdNumber(DEFAULT_NATIONAL_ID_NUMBER)
            .passportNumber(DEFAULT_PASSPORT_NUMBER)
            .cibAcountNumber(DEFAULT_CIB_ACOUNT_NUMBER)
            .cityCountry(DEFAULT_CITY_COUNTRY)
            .homeAddress(DEFAULT_HOME_ADDRESS);
        // Add required entity
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        employee.setUser(user);
        return employee;
    }

    @Before
    public void initTest() {
        employee = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmployee() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();

        // Create the Employee
        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employee)))
            .andExpect(status().isCreated());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate + 1);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getPersonalPhoneNumber()).isEqualTo(DEFAULT_PERSONAL_PHONE_NUMBER);
        assertThat(testEmployee.getWorkPhoneNumber()).isEqualTo(DEFAULT_WORK_PHONE_NUMBER);
        assertThat(testEmployee.getdOB()).isEqualTo(DEFAULT_D_OB);
        assertThat(testEmployee.getHireDate()).isEqualTo(DEFAULT_HIRE_DATE);
        assertThat(testEmployee.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testEmployee.getSocialInsuranceNumber()).isEqualTo(DEFAULT_SOCIAL_INSURANCE_NUMBER);
        assertThat(testEmployee.getNationality()).isEqualTo(DEFAULT_NATIONALITY);
        assertThat(testEmployee.getNationalIdNumber()).isEqualTo(DEFAULT_NATIONAL_ID_NUMBER);
        assertThat(testEmployee.getPassportNumber()).isEqualTo(DEFAULT_PASSPORT_NUMBER);
        assertThat(testEmployee.getCibAcountNumber()).isEqualTo(DEFAULT_CIB_ACOUNT_NUMBER);
        assertThat(testEmployee.getCityCountry()).isEqualTo(DEFAULT_CITY_COUNTRY);
        assertThat(testEmployee.getHomeAddress()).isEqualTo(DEFAULT_HOME_ADDRESS);
    }

    @Test
    @Transactional
    public void createEmployeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();

        // Create the Employee with an existing ID
        employee.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employee)))
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEmployees() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList
        restEmployeeMockMvc.perform(get("/api/employees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employee.getId().intValue())))
            .andExpect(jsonPath("$.[*].personalPhoneNumber").value(hasItem(DEFAULT_PERSONAL_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].workPhoneNumber").value(hasItem(DEFAULT_WORK_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].dOB").value(hasItem(DEFAULT_D_OB.toString())))
            .andExpect(jsonPath("$.[*].hireDate").value(hasItem(DEFAULT_HIRE_DATE.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].socialInsuranceNumber").value(hasItem(DEFAULT_SOCIAL_INSURANCE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY.toString())))
            .andExpect(jsonPath("$.[*].nationalIdNumber").value(hasItem(DEFAULT_NATIONAL_ID_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].passportNumber").value(hasItem(DEFAULT_PASSPORT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].cibAcountNumber").value(hasItem(DEFAULT_CIB_ACOUNT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].cityCountry").value(hasItem(DEFAULT_CITY_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].homeAddress").value(hasItem(DEFAULT_HOME_ADDRESS.toString())));
    }

    @Test
    @Transactional
    public void getEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get the employee
        restEmployeeMockMvc.perform(get("/api/employees/{id}", employee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(employee.getId().intValue()))
            .andExpect(jsonPath("$.personalPhoneNumber").value(DEFAULT_PERSONAL_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.workPhoneNumber").value(DEFAULT_WORK_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.dOB").value(DEFAULT_D_OB.toString()))
            .andExpect(jsonPath("$.hireDate").value(DEFAULT_HIRE_DATE.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.socialInsuranceNumber").value(DEFAULT_SOCIAL_INSURANCE_NUMBER.toString()))
            .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY.toString()))
            .andExpect(jsonPath("$.nationalIdNumber").value(DEFAULT_NATIONAL_ID_NUMBER.toString()))
            .andExpect(jsonPath("$.passportNumber").value(DEFAULT_PASSPORT_NUMBER.toString()))
            .andExpect(jsonPath("$.cibAcountNumber").value(DEFAULT_CIB_ACOUNT_NUMBER.toString()))
            .andExpect(jsonPath("$.cityCountry").value(DEFAULT_CITY_COUNTRY.toString()))
            .andExpect(jsonPath("$.homeAddress").value(DEFAULT_HOME_ADDRESS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEmployee() throws Exception {
        // Get the employee
        restEmployeeMockMvc.perform(get("/api/employees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);
        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Update the employee
        Employee updatedEmployee = employeeRepository.findOne(employee.getId());
        // Disconnect from session so that the updates on updatedEmployee are not directly saved in db
        em.detach(updatedEmployee);
        updatedEmployee
            .personalPhoneNumber(UPDATED_PERSONAL_PHONE_NUMBER)
            .workPhoneNumber(UPDATED_WORK_PHONE_NUMBER)
            .dOB(UPDATED_D_OB)
            .hireDate(UPDATED_HIRE_DATE)
            .title(UPDATED_TITLE)
            .socialInsuranceNumber(UPDATED_SOCIAL_INSURANCE_NUMBER)
            .nationality(UPDATED_NATIONALITY)
            .nationalIdNumber(UPDATED_NATIONAL_ID_NUMBER)
            .passportNumber(UPDATED_PASSPORT_NUMBER)
            .cibAcountNumber(UPDATED_CIB_ACOUNT_NUMBER)
            .cityCountry(UPDATED_CITY_COUNTRY)
            .homeAddress(UPDATED_HOME_ADDRESS);

        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEmployee)))
            .andExpect(status().isOk());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getPersonalPhoneNumber()).isEqualTo(UPDATED_PERSONAL_PHONE_NUMBER);
        assertThat(testEmployee.getWorkPhoneNumber()).isEqualTo(UPDATED_WORK_PHONE_NUMBER);
        assertThat(testEmployee.getdOB()).isEqualTo(UPDATED_D_OB);
        assertThat(testEmployee.getHireDate()).isEqualTo(UPDATED_HIRE_DATE);
        assertThat(testEmployee.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testEmployee.getSocialInsuranceNumber()).isEqualTo(UPDATED_SOCIAL_INSURANCE_NUMBER);
        assertThat(testEmployee.getNationality()).isEqualTo(UPDATED_NATIONALITY);
        assertThat(testEmployee.getNationalIdNumber()).isEqualTo(UPDATED_NATIONAL_ID_NUMBER);
        assertThat(testEmployee.getPassportNumber()).isEqualTo(UPDATED_PASSPORT_NUMBER);
        assertThat(testEmployee.getCibAcountNumber()).isEqualTo(UPDATED_CIB_ACOUNT_NUMBER);
        assertThat(testEmployee.getCityCountry()).isEqualTo(UPDATED_CITY_COUNTRY);
        assertThat(testEmployee.getHomeAddress()).isEqualTo(UPDATED_HOME_ADDRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingEmployee() throws Exception {
        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Create the Employee

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employee)))
            .andExpect(status().isCreated());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);
        int databaseSizeBeforeDelete = employeeRepository.findAll().size();

        // Get the employee
        restEmployeeMockMvc.perform(delete("/api/employees/{id}", employee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Employee.class);
        Employee employee1 = new Employee();
        employee1.setId(1L);
        Employee employee2 = new Employee();
        employee2.setId(employee1.getId());
        assertThat(employee1).isEqualTo(employee2);
        employee2.setId(2L);
        assertThat(employee1).isNotEqualTo(employee2);
        employee1.setId(null);
        assertThat(employee1).isNotEqualTo(employee2);
    }
}
