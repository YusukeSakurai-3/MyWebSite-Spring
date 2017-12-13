package com.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	List<Review> findByUserId(int id);

	List<Review> findById(int parseInt);

	List<Review> findByUserIdAndItemId(int id, int itemId);

	List<Review> findByItemId(int itemId);

	//List<Review> findByUserId(int id);



}
