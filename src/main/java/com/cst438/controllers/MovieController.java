package com.cst438.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Movie;
import com.cst438.domain.MovieRepository;
import com.cst438.domain.Price;
import com.cst438.domain.PriceRepository;
import com.cst438.domain.Room;
import com.cst438.domain.RoomRepository;
import com.cst438.domain.Schedule;
import com.cst438.dto.MovieDTO;
import com.cst438.dto.ScheduleDTO;

@RestController
@CrossOrigin
public class MovieController {
	
	@Autowired
    private MovieRepository movieRepository;
	
	@Autowired
    private PriceRepository priceRepository;
	
	
	@GetMapping("/movies")
    public MovieDTO[] getAllMovies() {
        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        MovieDTO[] result = new MovieDTO[movies.size()];
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            MovieDTO dto = new MovieDTO(
                    movie.getMovie_id(),
                    movie.getMovie_title(),
                    movie.getMovie_rating(),
                    movie.getMovie_length(),
                    movie.getPrice().getPrice_id()
            );
            result[i] = dto;
        }
        return result;
    }

	@GetMapping("/movie/{id}")
	public MovieDTO getMovie(@PathVariable("id") int id) {

		Movie movie = movieRepository.findById(id).orElse(null);
		if (movie == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie not found." + id );
		}

		MovieDTO dto = new MovieDTO(
				movie.getMovie_id(),
				movie.getMovie_title(),
				movie.getMovie_rating(),
				movie.getMovie_length(),
				movie.getPrice().getPrice_id()
		);
		return dto;
	}
	
	
	@PostMapping("/movie")
	@Transactional
	public void createMovie(@RequestBody MovieDTO adto) {
		Price p = priceRepository.findById(adto.priceId()).orElse(null);
		if (p == null) {
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "room not found or not authorized " + adto.priceId());
		}
		
		
		Movie ms = new Movie();
		ms.setMovie_title(adto.movieTitle());
		ms.setMovie_rating(adto.movieRating());
		ms.setMovie_length(adto.movieLength());
		ms.setPrice(p);
		movieRepository.save(ms);
	}
    
	
	
	 @DeleteMapping("/movie/{id}")
		public void deleteMovie(@PathVariable("id") int id) {
			Movie m = movieRepository.findById(id).orElse(null);
			if (m == null) {
				throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Movie not found or not authorized " + id);
			}
			
			movieRepository.deleteById(id);
			
		}
    
}
