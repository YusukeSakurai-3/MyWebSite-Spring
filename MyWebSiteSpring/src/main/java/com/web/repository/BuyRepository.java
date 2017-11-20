package com.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.model.Buy;

public interface BuyRepository extends CrudRepository<Buy, Long> {

	void saveAndFlush(Buy buyData);

}
