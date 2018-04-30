package com.hrsystem.repository;

import com.hrsystem.domain.Request;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Request entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("SELECT request from Request request where request.department.name like :department")
    List<Request> findRequestByDepartment(@Param("department") String department);

}
