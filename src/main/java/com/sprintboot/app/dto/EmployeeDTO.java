package com.sprintboot.app.dto;

import java.util.Optional;

import com.sprintboot.app.model.Employee;
import com.sprintboot.app.model.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String text;
	private Post post;
	public EmployeeDTO(Employee emp) {
		super();
		this.id = emp.getId();
		this.firstName = emp.getFirstName();
		this.lastName = emp.getLastName();
		this.email = emp.getEmail();
		this.text = emp.getText();
		this.post = (emp.getPost() != null) ? emp.getPost() : null;
	}
	
	

}
