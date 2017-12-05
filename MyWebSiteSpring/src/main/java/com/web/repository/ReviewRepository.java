package com.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

}
