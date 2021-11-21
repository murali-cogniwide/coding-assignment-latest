package com.flynow.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flynow.api.configuration.FlyNowConstants;
import com.flynow.api.entity.Plane;
import com.flynow.api.repository.PlaneRepository;
import com.flynow.api.repository.SearchRepository;
import com.flynow.api.utils.FlyNowUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class FlyNowPlaneService implements IFlyNowPlaneService {

	@Autowired
	private PlaneRepository planeRepository;

	@Autowired
	private SearchRepository searchRepository;

	public ResponseEntity<?> getPlanes() {
		List<Plane> planes = new ArrayList<>();
		Map<String, Object> getPlanesResponse = new HashMap<>();
		try {
			planes = (List<Plane>) planeRepository.findAll();
			return new ResponseEntity<>(planes, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			getPlanesResponse.put(FlyNowConstants.STATUS, FlyNowConstants.ERROR);
			getPlanesResponse.put(FlyNowConstants.DETAIL, e.getMessage());
			return new ResponseEntity<>(getPlanesResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	//This method is to get the planes with the given Sorting criteria.
	@Override
	public ResponseEntity<?> getPlanesSorted(String[] sort) {

		List<Order> orders = new ArrayList<Order>();

		if (sort[0].contains(",")) {
			// will sort more than 2 fields
			// sortOrder="field, direction"
			for (String sortOrder : sort) {
				String[] _sort = sortOrder.split(",");
				orders.add(new Order(FlyNowUtils.getSortDirection(_sort[1]), _sort[0])); //Calling the method getSortDirection(in Utils)
			}
		} else {
			// sort=[field, direction]
			orders.add(new Order(FlyNowUtils.getSortDirection(sort[1]), sort[0]));
		}

		List<Plane> planes = new ArrayList<>();
		Map<String, Object> getPlanesSortedResponse = new HashMap<>();
		try {
			planes = (List<Plane>) planeRepository.findAll(Sort.by(orders)); // Find All method with Sorting criteria as parameter
			return new ResponseEntity<>(planes, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			getPlanesSortedResponse.put(FlyNowConstants.STATUS, FlyNowConstants.ERROR);
			getPlanesSortedResponse.put(FlyNowConstants.DETAIL, e.getMessage());
			return new ResponseEntity<>(getPlanesSortedResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	// This method is to search the plane entity with its fields
	//Specification e.g --> ?search=destinationAirport:'Abhu Dhabi' OR originAirport:'Chennai'
	// This method uses third party dependency com.sipios for the search operations
	// Specifications based on the JPA criteria API 
	@Override
	public ResponseEntity<?> searchPlanes(Specification<Plane> specs) {

		List<Plane> planes = new ArrayList<>();
		Map<String, Object> searchPlanResponse = new HashMap<>();
		try {
			planes = (List<Plane>) searchRepository.findAll(Specification.where(specs));
			return new ResponseEntity<>(planes, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			searchPlanResponse.put(FlyNowConstants.STATUS, FlyNowConstants.ERROR);
			searchPlanResponse.put(FlyNowConstants.DETAIL, e.getMessage());
			return new ResponseEntity<>(searchPlanResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}