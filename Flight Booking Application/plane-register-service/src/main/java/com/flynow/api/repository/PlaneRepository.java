package com.flynow.api.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.flynow.api.entity.Plane;

//This JPA Repository is to create, read, and delete the db records using CrudRepository
@Repository
@Transactional
public interface PlaneRepository extends CrudRepository<Plane, Long>{

	List<Plane> findAll();
	List<Plane> findAll(Sort by);
	
}
	