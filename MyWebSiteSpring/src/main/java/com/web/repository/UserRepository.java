package com.web.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.web.model.User;

public interface UserRepository extends CrudRepository<User, Long> {


	//List<User> findByLoginId(String string);

	List<User> findByLoginIdAndPassword(String string, String string2);

	List<User> findByLoginId(String loginId);

	Page<User> findAll(Pageable pageable);


	Page<User> findByBirthDateBefore(Date formatEndDate, Pageable pageable);

	Page<User> findByName(String searchName,Pageable pageable);

	Page<User> findByNameContainsAndBirthDateBefore(String searchName, Date formatEndDate,Pageable pageable);

	Page<User> findByNameContainsAndBirthDateAfter(String searchName, Date formatStartDate,Pageable pageable);

	Page<User> findByNameContainsAndBirthDateAfterAndBirthDateBefore(String searchName, Date formatStartDate,
			Date formatEndDate, Pageable pageable);

	Page<User> findByNameContains(String searchName, Pageable pageable);

	User findById(int userId);






}
