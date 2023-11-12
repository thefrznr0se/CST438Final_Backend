package com.cst438.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Integer> {
    // Additional query methods, if needed
}
