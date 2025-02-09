package com.sprintboot.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprintboot.app.dto.EmployeeDTO;
import com.sprintboot.app.model.Employee;

//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
//	Note that we no need to add @Repository annotation because Spring Data JPA internally takes care of it.
	
	Page<Employee> findByPostId(Long id, Pageable pageable);
    Optional<Employee> findByIdAndPostId(Long empid, Long id);
    
    
    @Query("SELECT o FROM Employee o WHERE o.id = :id")
    Employee findEmployeeById(@Param("id") Long id);
//	<S> S save(EmployeeDTO employeeExist);

}
