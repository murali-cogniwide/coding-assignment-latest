package com.flynow.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flynow.api.entity.Plane;
import com.flynow.api.service.IFlyNowPlaneService;
import com.sipios.springsearch.anotation.SearchSpec;

@RestController
public class FlyNowController {

	@Autowired
	private IFlyNowPlaneService flyNowPlaneService;


	
	// Get the planes sorted by Specifications
	@GetMapping("/sorted/planes")
	public ResponseEntity<?> getPlanesSorted(@RequestParam(defaultValue = "id,desc") String[] sort) {

		return flyNowPlaneService.getPlanesSorted(sort);
	}

	// Get the planes with search filters
	@GetMapping("/search-filters/planes")
	public ResponseEntity<?> searchPlanes(@SearchSpec Specification<Plane> specs) {
		return flyNowPlaneService.searchPlanes(specs);
	}
	
	
}

// ################################################################
