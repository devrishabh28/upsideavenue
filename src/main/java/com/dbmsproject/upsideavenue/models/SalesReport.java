package com.dbmsproject.upsideavenue.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.dbmsproject.upsideavenue.models.Mode;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesReport {
    private Date saleDate;
    private String propertyName;
    private String seller;
    private String buyer;
    private Mode mode;
    private double price;
}
