package com.dbmsproject.upsideavenue.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dbmsproject.upsideavenue.models.Post;

public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("""
            select p from Post p inner join Property pr\s
            on p.propertyId=pr.propertyId\s
            where pr.owner.username = :owner\s
            and p.postStatus = PostStatus.AVAILABLE\s
            """)
    List<Post> findAllPostByOwner(String owner);
}
