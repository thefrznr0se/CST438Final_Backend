package com.cst438.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    // Additional query methods, if needed
}
