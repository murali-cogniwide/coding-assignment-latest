package com.flynow.api.service;



import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.flynow.api.entity.Plane;

public interface IFlyNowPlaneService {

	
	ResponseEntity<?> getPlanes();
	ResponseEntity<?> getPlanesSorted(String[] sort);	
	ResponseEntity<?> searchPlanes(Specification<Plane> specs);
	
}
