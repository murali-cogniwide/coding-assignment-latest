package com.flynow.api.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FlyNowUtils {

	public static Sort.Direction getSortDirection(String direction) {
		log.debug(direction);
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
	}

	public static List<Order> getSortOrders(String[] sort)
	{
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
	return orders;
	}
}
