package com.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.model.ItemGetList;

public interface ItemGetListRepository extends CrudRepository<ItemGetList, Long> {

	List<ItemGetList> findByUserId(int userId);

}
