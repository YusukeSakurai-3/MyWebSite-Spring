package com.web.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.web.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

	List<Item> findById(int i);

	//SELECT * FROM m_item ORDER BY RAND() LIMIT ?

	Page<Item> findAll(Pageable pageable);




	@Query("select i from Item i order by rand()")
    Page<Item> findRand(Pageable pageable);

}
