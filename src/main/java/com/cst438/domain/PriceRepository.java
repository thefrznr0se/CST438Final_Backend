package com.cst438.domain;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<Price, Integer> {
    // Additional query methods, if needed
}
