package com.cst438;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cst438.dto.ScheduleDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class JunitTestSchedule {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllSchedulesTest() throws Exception {
        MockHttpServletResponse response;

        response = mvc.perform(MockMvcRequestBuilders.get("/schedule").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        ScheduleDTO[] result = fromJsonString(response.getContentAsString(), ScheduleDTO[].class);

        assertEquals(result[0].movieTitle(), "Oppenheimer");
        assertEquals(result[0].date(), "2023-11-15");
        assertEquals(result[0].start_time(), "12:00 PM");
        assertEquals(result[0].end_time(), "2:00 PM");
        assertEquals(result[0].roomCapacity(), 50);

        assertEquals(result[12].movieTitle(), "Hidden Figures");
        assertEquals(result[12].date(), "2023-11-17");
        assertEquals(result[12].start_time(), "3:30 PM");
        assertEquals(result[12].end_time(), "6:00 PM");
        assertEquals(result[12].roomCapacity(), 50);
    }

    @Test
    public void getScheduleByIdTest() throws Exception {
        MockHttpServletResponse response;

        response = mvc.perform(MockMvcRequestBuilders.get("/schedule/3").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        ScheduleDTO result = fromJsonString(response.getContentAsString(), ScheduleDTO.class);

        assertEquals(result.scheduleId(), 3, "correct");
        assertEquals(result.movieTitle(), "Hidden Figures", "correct");
        assertEquals(result.start_time(), "7:00 PM", "correct");
        assertEquals(result.end_time(), "9:00 PM", "correct");
        assertEquals(result.date(), "2023-11-15", "correct");
    }

    @Test
    public void updateScheduleTest() throws Exception {
        MockHttpServletResponse response;

        response = mvc.perform(MockMvcRequestBuilders.get("/schedule/2").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        ScheduleDTO result = fromJsonString(response.getContentAsString(), ScheduleDTO.class);

        assertEquals(result.scheduleId(), 2, "correct");
        assertEquals(result.movieTitle(), "Barbie", "correct");
        assertEquals(result.start_time(), "3:00 PM", "correct");
        assertEquals(result.end_time(), "5:30 PM", "correct");
        assertEquals(result.date(), "2023-11-15", "correct");

        ScheduleDTO adto2 = new ScheduleDTO(2, "4:00 PM", "5:00 PM", "2023-11-17", 12,  "Cars", 1, null);
        response = mvc.perform(
                        MockMvcRequestBuilders
                                .put("/schedule/2")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(adto2))
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());

        // now get the assignment
        response = mvc.perform(
                        MockMvcRequestBuilders
                                .get("/schedule/2")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());
        ScheduleDTO adtor = fromJsonString(response.getContentAsString(), ScheduleDTO.class);
        assertNotEquals(result.movieId(), adtor.movieId());
        assertNotEquals(result.start_time(), adtor.start_time());
        assertNotEquals(result.end_time(), adtor.end_time());
        assertNotEquals(result.date(), adtor.date());
    }

    @Test
    public void createScheduleTest() throws Exception {
        ScheduleDTO adto = new ScheduleDTO(14, "2:00 PM", "4:00 PM", "2023-11-17", 12,  "Cars", 1, null);
        MockHttpServletResponse response;
        response = mvc.perform(
                        MockMvcRequestBuilders
                                .post("/schedule")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(adto))
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());

        response = mvc.perform(
                        MockMvcRequestBuilders
                                .get("/schedule/14")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());
        ScheduleDTO adtor = fromJsonString(response.getContentAsString(), ScheduleDTO.class);
        assertEquals(adto.start_time(), adtor.start_time());
        assertEquals(adto.end_time(), adtor.end_time());
        assertEquals(adto.date(), adtor.date());
        assertEquals(adto.movieTitle(), adtor.movieTitle());
        assertEquals(adto.movieId(), adtor.movieId());
        assertEquals(adto.roomId(), adtor.roomId());
    }

    @Test
    public void deleteScheduleTest() throws Exception {
        MockHttpServletResponse response;

        response = mvc.perform(MockMvcRequestBuilders.get("/schedule/13").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        ScheduleDTO result = fromJsonString(response.getContentAsString(), ScheduleDTO.class);

        assertEquals(result.scheduleId(), 13, "correct");
        assertEquals(result.movieTitle(), "Hidden Figures", "correct");
        assertEquals(result.start_time(), "3:30 PM", "correct");
        assertEquals(result.end_time(), "6:00 PM", "correct");
        assertEquals(result.date(), "2023-11-17", "correct");

        response = mvc
                .perform(MockMvcRequestBuilders.delete("/schedule/13").accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(result)).contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus(), "Schedule was Deleted");

        response = mvc.perform(MockMvcRequestBuilders.get("/schedule/13").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(400, response.getStatus(), "Schedule not found");
    }

    private static <T> T fromJsonString(String str, Class<T> valueType) {
        try {
            return new ObjectMapper().readValue(str, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String asJsonString(final Object obj) {
        try {

            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
