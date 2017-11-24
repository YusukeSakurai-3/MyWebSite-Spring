package com.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.model.BuyDetail;

public interface BuyDetailRepository extends CrudRepository<BuyDetail, Long> {

	List<BuyDetail> findById(int parseInt);

	List<BuyDetail> findByBuyId(int parseInt);

}
