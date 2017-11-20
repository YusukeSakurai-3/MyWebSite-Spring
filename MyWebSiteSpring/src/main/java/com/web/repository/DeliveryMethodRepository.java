package com.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.model.DeliveryMethod;

public interface DeliveryMethodRepository extends CrudRepository<DeliveryMethod, Long> {

	DeliveryMethod findById(int deliveryMethodId);

}
