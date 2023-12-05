package com.cst438;

import com.cst438.domain.Movie;
import com.cst438.dto.MovieDTO;
import com.cst438.dto.ScheduleDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class JunitTestMovie {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllMoviesTest() throws Exception {
        MockHttpServletResponse response;

        response = mvc.perform(MockMvcRequestBuilders.get("/movies").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        MovieDTO[] result = fromJsonString(response.getContentAsString(), MovieDTO[].class);

        assertEquals(result[0].movieTitle(), "Oppenheimer");
        assertEquals(result[0].movieRating(), "PG-13");
        assertEquals(result[0].movieLength(), 120);
        assertEquals(result[0].priceId(), 1);

        assertEquals(result[11].movieTitle(), "Cars");
        assertEquals(result[11].movieRating(), "PG-13");
        assertEquals(result[11].movieLength(), 90);
        assertEquals(result[11].priceId(), 3);
    }

    @Test
    public void getMovieByIdTest() throws Exception {
        MockHttpServletResponse response;

        response = mvc.perform(MockMvcRequestBuilders.get("/movie/3").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        MovieDTO result = fromJsonString(response.getContentAsString(), MovieDTO.class);

        assertEquals(result.movieId(), 3, "correct");
        assertEquals(result.movieTitle(), "Hidden Figures", "correct");
        assertEquals(result.movieRating(), "PG", "correct");
        assertEquals(result.movieLength(), 105, "correct");
        assertEquals(result.priceId(), 3, "correct");
    }

    @Test
    public void movieNotFoundTest() throws Exception {
        MockHttpServletResponse response;

        response = mvc.perform(MockMvcRequestBuilders.get("/movie/15").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    public void addMovieTest() throws Exception {
        MovieDTO adto = new MovieDTO(14, "Iron Man", "PG", 140, 1);
        MockHttpServletResponse response;
        response = mvc.perform(
                        MockMvcRequestBuilders
                                .post("/movie")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(adto))
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());

        response = mvc.perform(
                        MockMvcRequestBuilders
                                .get("/movie/14")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());
        MovieDTO adtor = fromJsonString(response.getContentAsString(), MovieDTO.class);
        assertEquals(adto.movieTitle(), adtor.movieTitle());
        assertEquals(adto.movieRating(), adtor.movieRating());
        assertEquals(adto.movieLength(), adtor.movieLength());
        assertEquals(adto.priceId(), adtor.priceId());
    }

    @Test
    public void deleteMovieTest() throws Exception {
        MockHttpServletResponse response;

        response = mvc.perform(MockMvcRequestBuilders.get("/movie/12").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        MovieDTO result = fromJsonString(response.getContentAsString(), MovieDTO.class);

        assertEquals(result.movieId(), 12, "correct");
        assertEquals(result.movieTitle(), "Cars", "correct");
        assertEquals(result.movieRating(), "PG-13", "correct");
        assertEquals(result.movieLength(), 90, "correct");

        response = mvc
                .perform(MockMvcRequestBuilders.delete("/movie/12").accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(result)).contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus(), "Movie was Deleted");

        response = mvc.perform(MockMvcRequestBuilders.get("/movie/12").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(400, response.getStatus(), "Movie not found");
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
