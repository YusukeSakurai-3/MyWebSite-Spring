package com.web.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.web.model.Item;
import com.web.model.ItemData;

public interface ItemRepository extends CrudRepository<Item, Long> {

	List<Item> findById(int i);

	//SELECT * FROM m_item ORDER BY RAND() LIMIT ?

	//Page<Item> findAll(Specifications<Item> specifications);




	@Query("select i from Item i order by rand()")
    Page<Item> findRand(Pageable pageable);

	List<Item> findByName(String searchWord);

	Page<Item> findByNameContains(String searchWord,Pageable pageable);

	Page<Item> findAll(Pageable pageable);


//	count(i.buyDetail.itemId)
	@Query("select new com.web.model.ItemData(i, 12) from Item i " +
			"left outer JOIN i.buyDetailList "+
			" group by i.id" )
//			"on i.id = i.buyDetail ")
	List<ItemData> findItemData();




}
