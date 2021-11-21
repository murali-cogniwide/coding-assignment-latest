package com.flynow.api.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flynow.api.configuration.FlyNowConstants;
import com.flynow.api.entity.Plane;
import com.flynow.api.model.Airline;
import com.flynow.api.model.Manufacturer;
import com.flynow.api.repository.PlaneRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class FlyNowPlaneService implements IFlyNowPlaneService {

	@Autowired
	private PlaneRepository planeRepository;

	// Register Plane Method uses 'Plane' Object
	@Override
	public ResponseEntity<?> registerPlane(Plane plane) {

		Map<String, Object> registerPlaneResponse = new HashMap<>();
		try {

			if (Objects.nonNull(plane.getAirline()) && Objects.nonNull(plane.getManufacturer())) {
				log.debug("Airline and Manufacturer info valid");
				String airlineId = Airline.getAirlineIdByAirlineName(plane.getAirline().trim());
				String manufacturerId = Manufacturer.valueOf(plane.getManufacturer().toUpperCase()).toString();
				if (airlineId != null) {

					plane.setAirlineId(airlineId);

					plane.setManufacturer(manufacturerId);
					plane.setStatus(FlyNowConstants.ACTIVE);
					Plane planeResponse = planeRepository.save(plane);
					log.debug("Plane registeration success");
					return new ResponseEntity<>(planeResponse, new HttpHeaders(), HttpStatus.CREATED);
				} else {
					log.debug("Plane registeration failed.Airline {" + plane.getAirline() + "} not registered");
					registerPlaneResponse.put(FlyNowConstants.STATUS, FlyNowConstants.ERROR);
					registerPlaneResponse.put(FlyNowConstants.DETAIL, FlyNowConstants.AIRLINE_NOT_REGISTERED);
					return new ResponseEntity<>(registerPlaneResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
				}
			} else {
				log.debug("Plane registeration failed.Invalid Airline or  Manufacturer");
				registerPlaneResponse.put(FlyNowConstants.STATUS, FlyNowConstants.ERROR);
				registerPlaneResponse.put(FlyNowConstants.DETAIL, FlyNowConstants.AIRLINE_AND_MANUFACTURER_MANDATORY);
				return new ResponseEntity<>(registerPlaneResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			if (e.getMessage().toLowerCase().contains(FlyNowConstants.ENUM_CONST_NOT_FOUND)) {
				log.error(e.getMessage());
				registerPlaneResponse.put(FlyNowConstants.DETAIL, FlyNowConstants.ENUM_CONST_NOT_FOUND);
				return new ResponseEntity<>(registerPlaneResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}
			registerPlaneResponse.put(FlyNowConstants.STATUS, FlyNowConstants.ERROR);
			registerPlaneResponse.put(FlyNowConstants.DETAIL, e.getMessage());
			return new ResponseEntity<>(registerPlaneResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Delete the plane record - Soft Deletion
	// Get the record by Plane ID and update the status to 'Inactive' and save the
	// record.
	@Override
	public ResponseEntity<?> deletePlane(long planeId) {
		Map<String, String> deleteReponse = new HashMap<>();

		;
		try {
			Optional<Plane> plane = planeRepository.findById(planeId);
			Plane planeRecordReturnedFromDb = plane.get();
			planeRecordReturnedFromDb.setStatus(FlyNowConstants.INACTIVE);
			Plane planeResponse = planeRepository.save(planeRecordReturnedFromDb);
			if (planeResponse.getStatus().equals(FlyNowConstants.INACTIVE)) {
				deleteReponse.put(FlyNowConstants.STATUS, FlyNowConstants.SUCCESS);
				return new ResponseEntity<>(deleteReponse, new HttpHeaders(), HttpStatus.OK);
			}

			else {
				deleteReponse.put(FlyNowConstants.STATUS, FlyNowConstants.ERROR);
				deleteReponse.put(FlyNowConstants.DETAIL, FlyNowConstants.NOT_FOUND);
				return new ResponseEntity<>(deleteReponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			if (e.getCause().toString().contains(FlyNowConstants.NO_SUCH_ELEMENT_EXCEPTION)) {
				deleteReponse.put(FlyNowConstants.DETAIL, FlyNowConstants.NOT_FOUND);
			}
			deleteReponse.put(FlyNowConstants.STATUS, FlyNowConstants.ERROR);
			deleteReponse.put(FlyNowConstants.DETAIL, FlyNowConstants.NOT_FOUND);
			return new ResponseEntity<>(deleteReponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
