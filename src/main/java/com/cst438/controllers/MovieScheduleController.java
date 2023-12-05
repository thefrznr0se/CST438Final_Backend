package com.cst438.controllers;

import com.cst438.domain.Schedule;
import com.cst438.domain.Movie;
import com.cst438.domain.MovieRepository;
import com.cst438.domain.Room;
import com.cst438.domain.ScheduleRepository;
import com.cst438.dto.ScheduleDTO;
import com.cst438.domain.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
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
	public ScheduleDTO[] getAllScheduleItems() {


		List<Schedule> scheduleItems = (List<Schedule>) scheduleRepository.findAll();
		ScheduleDTO[] result = new ScheduleDTO[scheduleItems.size()];
		for (int i = 0; i < scheduleItems.size(); i++) {
			Schedule schedule = scheduleItems.get(i);
			ScheduleDTO dto = new ScheduleDTO(
					schedule.getSchedule_id(),
					schedule.getStart_time(),
					schedule.getEnd_time(),
					schedule.getDate().toString(),
					schedule.getMovie().getMovie_id(),
					schedule.getMovie().getMovie_title(),
					schedule.getRoom().getRoom_id(),
					schedule.getRoom().getCapacity()
			);
			result[i] = dto;
		}
		return result;
	}


	@GetMapping("/schedule/{id}")
	public ScheduleDTO getSchedule(@PathVariable("id") int id) {

		Schedule schedules = scheduleRepository.findById(id).orElse(null);
		if (schedules == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "schedule not found or not authorized." + id );
		}

		ScheduleDTO dto = new ScheduleDTO(
				schedules.getSchedule_id(),
				schedules.getStart_time(),
				schedules.getEnd_time(),
				schedules.getDate().toString(),
				schedules.getMovie().getMovie_id(),
				schedules.getMovie().getMovie_title(),
				schedules.getRoom().getRoom_id(),
				schedules.getRoom().getCapacity()
		);
		return dto;
	}


	@PutMapping("/schedule/{id}")
	public void updateSchedule(@PathVariable("id") int id, @RequestBody ScheduleDTO adto) {

		Schedule s = scheduleRepository.findById(id).orElse(null);
		if (s ==null) {
			throw  new ResponseStatusException( HttpStatus.NOT_FOUND, "schedule not found or not authorized "+id);
		}

		Movie m = movieRepository.findByTitle(adto.movieTitle());
		if (m ==null) {
			throw  new ResponseStatusException( HttpStatus.NOT_FOUND, "movie not found or not authorized "+ adto.movieId());
		}

		Room r = roomRepository.findById(adto.roomId()).orElse(null);
		if (r == null) {
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Room not found or not authorized " + adto.roomId());
		}

		s.setDate(java.sql.Date.valueOf(adto.date()));
		s.setStart_time(adto.start_time());
		s.setEnd_time(adto.end_time());
		s.setMovie(m);
		s.setRoom(r);
		scheduleRepository.save(s);
	}


	@PostMapping("/schedule")
	@Transactional
	public void createSchedule(@RequestBody ScheduleDTO adto) {
		Movie m = movieRepository.findByTitle(adto.movieTitle());
		if (m == null) {
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "movie not found or not authorized " + adto.movieId());
		}

		Room r = roomRepository.findById(adto.roomId()).orElse(null);
		if (r == null) {
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "room not found or not authorized " + adto.roomId());
		}


		Schedule s = new Schedule();
		s.setDate(java.sql.Date.valueOf(adto.date()));
		s.setStart_time(adto.start_time());
		s.setEnd_time(adto.end_time());
		s.setMovie(m);
		s.setRoom(r);
		scheduleRepository.save(s);
	}


	@DeleteMapping("/schedule/{id}")
	public void deleteSchedule(@PathVariable("id") int id) {
		Schedule s = scheduleRepository.findById(id).orElse(null);
		if (s==null) {
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "room not found or not authorized " + id);
		}

		scheduleRepository.deleteById(id);

	}


}
