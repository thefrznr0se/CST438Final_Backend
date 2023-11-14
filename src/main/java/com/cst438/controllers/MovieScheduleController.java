package com.cst438.controllers;

import com.cst438.domain.Schedule;
import com.cst438.domain.MovieRepository;
import com.cst438.domain.ScheduleRepository;
import com.cst438.dto.ScheduleDTO;
import com.cst438.domain.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class MovieScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RoomRepository roomRepository;
    
    @GetMapping("/schedule")
    public ScheduleDTO[] getAllScheduleItems() {
        List<Schedule> scheduleItems = (List<Schedule>) scheduleRepository.findAll();
        ScheduleDTO[] result = new ScheduleDTO[scheduleItems.size()];
        for (int i = 0; i < scheduleItems.size(); i++) {
            Schedule schedule = scheduleItems.get(i);
            ScheduleDTO dto = new ScheduleDTO(
                    schedule.getSchedule_id(),
                    schedule.getStart_time(),
                    schedule.getEnd_time(),
                    schedule.getDate(),
                    schedule.getMovie().getMovie_id(),
                    schedule.getRoom().getRoom_id()
            );
            result[i] = dto;
        }
        return result;
    }
}
