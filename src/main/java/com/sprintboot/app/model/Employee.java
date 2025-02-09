package com.sprintboot.app.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee extends AuditModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name", nullable = false)
	// first name should not be null or empty
	// first name should have at least 3 characters
	@NotEmpty
	@Size(min = 3, message = "First name should have at least 3 characters.")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	// email should be a valid email format
	// email should not be null or empty
	@NotEmpty
	@Email
	private String email;

	@NotNull
	@Lob
	private String text;
	
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="post_id", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	/*
	 * @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class)
	 * 
	 * @JsonIdentityReference(alwaysAsId = true)
	 * 
	 * @JsonProperty("post_id")
	 */
	private Post post;

//	@NotEmpty validates that the property is not null or empty; can be applied to String, Collection, Map, or Array values.
//	@Size validates that the annotated property value has a size between the attributes min and max; can be applied to String, Collection, Map, and array properties.
//	@Email validates that the annotated property is a valid email address.

//	using Lombok annotation @Data to reduce the boilerplate code (getters/setters).

}
