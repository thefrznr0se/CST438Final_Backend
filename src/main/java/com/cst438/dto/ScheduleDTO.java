package com.cst438.dto;
import java.sql.Date;

public record ScheduleDTO(int scheduleId, String start_time, String end_time, Date date, int movieId, int roomId) {
	
}