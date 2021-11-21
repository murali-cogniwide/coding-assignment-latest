package com.flynow.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "plane")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Plane {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="plane_id")
	private long planeId;
	
	@Column(name="plane_manufacturer")
	@NotBlank(message = "Manufacturer is mandatory")
	private String manufacturer;
	
		
	@Column(name="airline_id")
	private String airlineId;
	
	@Column(name="airline_name")
	@NotBlank(message = "Airline Name is mandatory")
	private String airline;
	
	
	@Column(name="passenger_capacity")
	@NotBlank(message = "Passenger Capacity is mandatory")
	private String passengerCapacity;
	
	@Column(name="origin_airport")
	@NotBlank(message = "Origin Airport is mandatory")
	private String originAirport;
	
	
	@Column(name="destination_airport")
	@NotBlank(message = "Destination Aiport is mandatory")
	private String destinationAirport;
	
	@Column(name="fare")
	@NotBlank(message = "Fare(AED) is mandatory")
	private String fareInAED;
	
	@Column(name="status")
	private String status;
	
	

}
