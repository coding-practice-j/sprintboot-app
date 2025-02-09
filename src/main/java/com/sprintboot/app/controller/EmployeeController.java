package com.sprintboot.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sprintboot.app.dto.EmployeeDTO;
import com.sprintboot.app.exceptions.ResourceNotFoundException;
import com.sprintboot.app.model.Employee;
import com.sprintboot.app.repository.EmployeeRepository;
import com.sprintboot.app.repository.PostRepository;
import com.sprintboot.app.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService; // Injecting dependency using constructor
	}

	// build create employee REST API
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}

	// build get all employees REST API
	@GetMapping
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	// build get employee by id REST API
	// http://localhost:8080/api/employees/1
	@GetMapping("{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") long employeeId) {
		return new ResponseEntity<EmployeeDTO>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}

	// build update employee REST API
	// http://localhost:8080/api/employees/1
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @Valid @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
	}

	// build delete employee REST API
	// http://localhost:8080/api/employees/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {

		// delete employee from DB
		employeeService.deleteEmployee(id);

		return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
	}

	// Get employee based on postId using pagination
	// http://localhost:8080/api/employees/posts/1/employee?page=0&size=2&sort=id,desc
	@GetMapping("posts/{postId}/employee")
	public Page<Employee> getAllCommentsByPostId(@PathVariable(value = "postId") Long postId, Pageable pageable) {
		return employeeRepository.findByPostId(postId, pageable);
	}

	@PostMapping("/posts/{postId}/employee")
	public Employee createEmployee(@PathVariable(value = "postId") Long postId, @Valid @RequestBody Employee employee) {
		return postRepository.findById(postId).map(post -> {
			System.out.println("post:::" + post.getTitle());
			employee.setPost(post);
			return employeeRepository.save(employee);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
	}

	@PutMapping("/posts/{postId}/employee/{empId}")
	public Employee updateComment(@PathVariable(value = "postId") Long postId, @PathVariable(value = "empId") Long id,
			@Valid @RequestBody Employee employeeRequest) {
		if (!postRepository.existsById(postId)) {
			throw new ResourceNotFoundException("PostId " + postId + " not found");
		}

		return employeeRepository.findById(id).map(employee -> {
			employee.setText(employeeRequest.getText());
			return employeeRepository.save(employee);
		}).orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));
	}
	
	
	@DeleteMapping("/posts/{postId}/employee/{empId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
                              @PathVariable (value = "empId") Long empId) {
        return employeeRepository.findByIdAndPostId(empId, postId).map(comment -> {
            employeeRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + empId + " and postId " + postId));
    }

}
