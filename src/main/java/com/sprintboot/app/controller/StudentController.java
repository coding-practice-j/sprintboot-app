package com.sprintboot.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprintboot.app.domain.Student;

@RestController
public class StudentController {

	/*
	 * Spring’s HTTP message converter support, you don’t need to do this conversion
	 * manually. Because Jackson 2 is on the classpath, Spring’s
	 * MappingJackson2HttpMessageConverter is automatically chosen to convert the
	 * Student instance to JSON.
	 */
	// http://localhost:8080/student
	@GetMapping("/student")
	public Student getStudent() {
		return new Student("Ramesh", "Fadatare");
	}

	// http://localhost:8080/student/1
	// @PathVariable annotation
	@GetMapping("/student/{firstName}/{lastName}/")
	public Student studentPathVariable(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName) {
		return new Student(firstName, lastName);
	}

	// build rest API to handle query parameters
	// http://localhost:8080/student/query?firstName=Ramesh&lastName=Fadatare
	@GetMapping("/student/query")
	public Student studentQueryParam(@RequestParam(name = "firstName") String firstName,@RequestParam(name = "lastName") String lastName) {
		return new Student(firstName, lastName);
	}

}
