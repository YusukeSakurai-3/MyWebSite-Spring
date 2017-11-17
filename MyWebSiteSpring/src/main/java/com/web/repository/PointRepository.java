package com.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.model.Point;

public interface PointRepository extends CrudRepository<Point, Long> {

	 Point findByUserId(int id) ;



}
