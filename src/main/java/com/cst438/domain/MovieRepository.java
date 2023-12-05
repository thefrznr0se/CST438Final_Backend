package com.cst438.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

	@Query("select a from Movie a where a.movie_title= :movieTitle")
	Movie findByTitle(@Param("movieTitle") String movie_title);
}
