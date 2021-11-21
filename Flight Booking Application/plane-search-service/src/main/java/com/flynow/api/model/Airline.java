package com.flynow.api.model;

import java.util.Arrays;
import java.util.NoSuchElementException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public enum Airline {

	// Airline(Airline ID, Airline Name, Total Planes, Airline Operating Status)

	QATAR_AIRLINES("QA001", "Qatar Airlines", 50, "Inservice"),
	SINGAPORE_AIRLINES("SA002", "Singapore Airlines", 30, "Inservice"),
	BRITISH_AIRWAYS("BA003", "British Airways", 20, "Inservice"),
	AMERICAN_AIRLINES("AA004", "American Airlines", 15, "Inservice"),
	MIDWAY_AIRLINES("MA005", "Midway Airlines", 2, "OutofService");

	public final String id;
	public final String name;
	public final int totalPlanes;
	public final String status;

	//Get Airlines by Airline ID
	public static String getAirlineById(String id) {
		try {
			return Arrays.stream(Airline.values()).filter(airline -> airline.id.equals(id)).findFirst().get().name
					.toString();
		} catch (NoSuchElementException ex) {
			return null;
		}

	}
	
	////Get Airlines by Airline Total Planes
	public static String getAirlineByTotalPlanes(int totalPlanes) {
		try {
			return Arrays.stream(Airline.values()).filter(airline -> airline.totalPlanes == totalPlanes).findFirst()
					.get().name.toString();
		} catch (NoSuchElementException ex) {
			return null;
		}
	}
	
	//Get Airline ID by Airline Name
	public static String getAirlineIdByAirlineName(String airlineName) {
		try {
			return Arrays.stream(Airline.values()).filter(airline -> airline.name.equals(airlineName)).findFirst()
					.get().id.toString();
		} catch (NoSuchElementException ex) {
			return null;
		}

	}

}
