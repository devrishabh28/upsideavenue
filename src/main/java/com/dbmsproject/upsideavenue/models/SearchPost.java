package com.dbmsproject.upsideavenue.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchPost {
    private String city;
    private String state;

    private Double minPrice;
    private Double maxPrice;

    private Mode mode;

    private Integer minSize;
    private Integer maxSize;

    private Furnished furnished;

    private Integer minBedrooms;
    private Integer maxBedrooms;

}
