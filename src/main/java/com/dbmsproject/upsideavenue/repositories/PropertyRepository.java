package com.dbmsproject.upsideavenue.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbmsproject.upsideavenue.models.Property;

public interface PropertyRepository extends JpaRepository<Property, UUID> {

    List<Property> findByOwner();

}
