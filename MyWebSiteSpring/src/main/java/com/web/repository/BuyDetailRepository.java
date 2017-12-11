package com.web.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.web.model.BuyDetail;
import com.web.model.BuyRanking;

public interface BuyDetailRepository extends CrudRepository<BuyDetail, Long> {

	List<BuyDetail> findById(int parseInt);

	List<BuyDetail> findByBuyId(int parseInt);

	//Query("select i from Item i order by rand()")new com.web.model.class.BuyRanking(b.itemId, count(b.itemId))
	@Query("select new com.web.model.BuyRanking(b.itemId, count(b.itemId)) "
			+ "from BuyDetail b GROUP BY b.itemId ORDER BY count(b.itemId) DESC")
    Page<BuyRanking> findRanking(Pageable pageable);

	@Query("select new java.lang.Long(count(b.itemId)) from BuyDetail b WHERE b.itemId = :itemId GROUP BY b.itemId")
	List<Long> findCount(@Param("itemId")int itemId);

//	@Query("select new com.web.model.ItemData(b.item,count(b.itemId)) from BuyDetail b " +
//			"right outer join b.item " +
//			"on b.itemId = b.item.id " +
//			"group by b.item.id")
//	List<ItemData> findItemData();






}
