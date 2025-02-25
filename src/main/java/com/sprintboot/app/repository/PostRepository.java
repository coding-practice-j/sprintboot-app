package com.sprintboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprintboot.app.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
