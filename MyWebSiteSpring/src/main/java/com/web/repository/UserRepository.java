package com.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.model.User;

public interface UserRepository extends CrudRepository<User, Long> {


	//List<User> findByLoginId(String string);

	List<User> findByLoginIdAndPassword(String string, String string2);






}
