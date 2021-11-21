package com.flynow.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flynow.api.entity.Plane;
import com.flynow.api.service.IFlyNowPlaneService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FlyNowController {

	@Autowired
	private IFlyNowPlaneService flyNowPlaneService;


	// Register new plane
	@PostMapping("/plane")
	public ResponseEntity<?> registerPlane(@RequestBody Plane plane) {
		log.debug("Register Plane");
		return flyNowPlaneService.registerPlane(plane);
	}

		
	//Soft Delete - Plane details (it updates the DB by setting the staus as Inactive
	@DeleteMapping("/plane/{planeId}")
	public ResponseEntity<?> deletePlanes(@PathVariable long planeId) {

		return flyNowPlaneService.deletePlane(planeId);

	}
}

// ################################################################
