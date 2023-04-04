package com.dbmsproject.upsideavenue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbmsproject.upsideavenue.models.Sales;
import com.dbmsproject.upsideavenue.models.primaryIds;


public interface SalesRepository extends JpaRepository<Sales,primaryIds> {
    
}
