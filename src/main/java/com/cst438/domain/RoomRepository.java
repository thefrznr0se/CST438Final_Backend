package com.cst438.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    // Additional query methods, if needed
}
