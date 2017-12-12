package com.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.model.Point;

public interface PointRepository extends CrudRepository<Point, Long> {

	 List<Point> findByUserId(int id) ;

	void flush();



}
