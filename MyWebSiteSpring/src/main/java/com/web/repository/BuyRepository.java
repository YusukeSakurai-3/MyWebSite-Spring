package com.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.model.Buy;

public interface BuyRepository extends CrudRepository<Buy, Long> {

	List<Buy> findByUserId(int id);

	List<Buy> findById(int parseInt);


}
