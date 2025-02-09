package com.sprintboot.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.sprintboot.app.dto.EmployeeDTO;
import com.sprintboot.app.exceptions.ResourceNotFoundException;
import com.sprintboot.app.model.Employee;
import com.sprintboot.app.repository.EmployeeRepository;
import com.sprintboot.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public EmployeeDTO getEmployeeById(long id) {
		Employee emp = employeeRepository.findEmployeeById(id);
		if(emp == null) {
			throw new ResourceNotFoundException("Employee","Id", id);
		}else {
			System.out.println("Employee!!!!!");
			return new EmployeeDTO(emp);
		}
	}

	/* This will not be suitable if post will null for any employee */
	@Override
	public Employee updateEmployee(Employee employee, long id) {
		/*we need to check whether employee with given id is exist in DB/* or not */
		Employee employeeExist = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","Id", id));	
		
		employeeExist.setFirstName(employee.getFirstName());
		employeeExist.setLastName(employee.getLastName());
		employeeExist.setEmail(employee.getEmail());
		employeeExist.setText(employee.getText());

		// save existing employee to DB
		employeeRepository.save(employeeExist);
		return (Employee)employeeExist;
	}

	@Override
	public void deleteEmployee(long id) {

		Employee emp = employeeRepository.findEmployeeById(id);
		if(emp != null) {
			employeeRepository.deleteById(id);
		}else {
			throw new ResourceNotFoundException("Employee","Id", id);
		}
		
	}

}
