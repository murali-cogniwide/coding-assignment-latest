package com.flynow.api.service;



import org.springframework.http.ResponseEntity;

import com.flynow.api.entity.Plane;

public interface IFlyNowPlaneService {

	ResponseEntity<?> registerPlane(Plane plane);
	ResponseEntity<?> deletePlane(long planeId);

}
