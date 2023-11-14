package com.cst438.domain;

import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Integer> {
    // Additional query methods, if needed
}
