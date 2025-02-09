package com.sprintboot.app.service;

import java.util.List;

import com.sprintboot.app.dto.EmployeeDTO;
import com.sprintboot.app.model.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);

	List<Employee> getAllEmployees();

	EmployeeDTO getEmployeeById(long id);

	Employee updateEmployee(Employee employee, long id);

	void deleteEmployee(long id);

}
