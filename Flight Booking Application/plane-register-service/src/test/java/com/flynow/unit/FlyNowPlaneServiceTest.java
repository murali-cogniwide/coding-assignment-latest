package com.flynow.unit;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.flynow.api.configuration.FlyNowConstants;
import com.flynow.api.entity.Plane;
import com.flynow.api.repository.PlaneRepository;

import com.flynow.api.service.IFlyNowPlaneService;
import com.flynow.api.utils.FlyNowUtils;


public class FlyNowPlaneServiceTest {

	@Autowired
	IFlyNowPlaneService flyNowPlaneService;

	@Autowired
	private PlaneRepository planeRepository;

	
	private static Logger log = LoggerFactory.getLogger(FlyNowPlaneServiceTest.class);
	// Register Plane - Unit Test
	@Test
	void testRegisterPlane() {

		try {
			Plane plane = createPlaneObject();
			Plane planeResponse = planeRepository.save(plane);
			long planeId = planeResponse.getPlaneId();
			assertEquals(FlyNowConstants.ACTIVE, planeResponse.getStatus());
			planeRepository.deleteById(planeId);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	// List Planes - Unit Test
	@Test
	void testListPlanes() {

		try {

			List<Plane> planeResponse = planeRepository.findAll();

			assertEquals(true, !planeResponse.isEmpty());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	// Delete Planes - Unit Test
	@Test
	void testDeletePlanes() {
		try {
			Plane plane = createPlaneObject();
			Plane planeResponse = planeRepository.save(plane);
			long planeId = planeResponse.getPlaneId();
			Optional<Plane> getPlaneRecord = planeRepository.findById(planeId);
			Plane planeRecordReturnedFromDb = getPlaneRecord.get();
			planeRecordReturnedFromDb.setStatus(FlyNowConstants.INACTIVE);
			Plane planeSoftDeleteReponse = planeRepository.save(planeRecordReturnedFromDb);
			assertEquals(FlyNowConstants.INACTIVE, planeSoftDeleteReponse.getStatus());
			planeRepository.deleteById(planeId);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

	// Sort Planes - Unit Test
	@Test
	void testSortPlane() {

		try {
			String[] sort = { "manufacturer", "desc" };
			List<Order> orders = FlyNowUtils.getSortOrders(sort);
			List<Plane> planeResponse = planeRepository.findAll(Sort.by(orders));

			assertEquals(true, !planeResponse.isEmpty());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

	// Hard coded Method for creating plane object
	Plane createPlaneObject() {
		Plane plane = new Plane();
		plane.setAirline("American Airlines");
		plane.setManufacturer("BOEING");
		plane.setOriginAirport("Dallas");
		plane.setDestinationAirport("Madurai");
		plane.setPassengerCapacity("150");
		plane.setFareInAED("9030.000");
		plane.setStatus("ACTIVE");
		plane.setAirlineId("A004");

		return plane;

	}

}
