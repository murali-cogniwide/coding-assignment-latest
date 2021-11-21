package com.flynow.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.flynow.api.entity.Plane;

//This Repository is created for searching the records by given specifications using JpaSpecificationExecutor
@RepositoryRestResource
public interface SearchRepository extends JpaRepository<Plane, Long>, JpaSpecificationExecutor<Plane> {



}


