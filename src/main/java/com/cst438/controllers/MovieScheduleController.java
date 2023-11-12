package com.cst438.controllers;

import com.cst438.domain.Schedule;
import com.cst438.domain.MovieRepository;
import com.cst438.domain.ScheduleRepository;
import com.cst438.domain.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
    public List<Schedule> getMovieSchedule() {
        return scheduleRepository.findAll();
    }

    @GetMapping("/schedule/{id}")
    @PreAuthorize("hasRole('USER')")
    public Schedule getMovieScheduleById(@PathVariable("id") int id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @PostMapping("/schedule")
    @PreAuthorize("hasRole('ADMIN')")
    public int createMovieSchedule(@RequestBody Schedule schedule) {
        // Check if movie and room exist
        if (movieRepository.findById(schedule.getMovie().getMovie_id()).isEmpty() ||
                roomRepository.findById(schedule.getRoom().getRoom_id()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid movie or room ID");
        }

        // Save the schedule
        scheduleRepository.save(schedule);
        return schedule.getSchedule_id();
    }

    @PutMapping("/schedule/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateMovieSchedule(@PathVariable("id") int id, @RequestBody Schedule updatedSchedule) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);
        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found with ID: " + id);
        }

        // Check if movie and room exist
        if (movieRepository.findById(updatedSchedule.getMovie().getMovie_id()).isEmpty() ||
                roomRepository.findById(updatedSchedule.getRoom().getRoom_id()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid movie or room ID");
        }

        // Update schedule details
        schedule.setStart_time(updatedSchedule.getStart_time());
        schedule.setEnd_time(updatedSchedule.getEnd_time());
        schedule.setDate(updatedSchedule.getDate());
        schedule.setMovie(updatedSchedule.getMovie());
        schedule.setRoom(updatedSchedule.getRoom());

        scheduleRepository.save(schedule);
    }

    @DeleteMapping("/schedule/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteMovieSchedule(@PathVariable("id") int id) {
        scheduleRepository.deleteById(id);
    }
}
