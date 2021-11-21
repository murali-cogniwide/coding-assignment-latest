package com.flynow.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Manufacturer {
	//Manufacturer(ID)
	BOEING("BOE001"),
	AIRBUS("AIRB002"),
	EMBRAER("EMB003");

	public String manufacturerId;
	
	
}
