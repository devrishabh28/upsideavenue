package com.dbmsproject.upsideavenue.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbmsproject.upsideavenue.models.Post;

public interface PostRepository extends JpaRepository<Post, UUID> {
    
}
