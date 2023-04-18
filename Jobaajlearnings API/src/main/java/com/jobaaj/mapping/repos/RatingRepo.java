package com.jobaaj.mapping.repos;



import org.springframework.data.jpa.repository.JpaRepository;

import com.jobaaj.mapping.entity.Rating;


public interface RatingRepo extends JpaRepository<Rating, Integer> {
	


}
