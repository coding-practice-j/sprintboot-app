package com.sprintboot.app.dto;

import lombok.Data;

@Data
public class PostDTO {
	private Long id;
	private String title;
	private String description;
	private String content;
}
