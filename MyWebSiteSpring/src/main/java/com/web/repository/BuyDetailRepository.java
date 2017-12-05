package com.web.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.web.model.BuyDetail;
import com.web.model.BuyRanking;

public interface BuyDetailRepository extends CrudRepository<BuyDetail, Long> {

	List<BuyDetail> findById(int parseInt);

	List<BuyDetail> findByBuyId(int parseInt);

	//Query("select i from Item i order by rand()")new com.web.model.class.BuyRanking(b.itemId, count(b.itemId))
	@Query("select new com.web.model.BuyRanking(b.itemId, count(b.itemId)) from BuyDetail b GROUP BY b.itemId ORDER BY count(b.itemId) DESC")
    Page<BuyRanking> findRanking(Pageable pageable);
}
