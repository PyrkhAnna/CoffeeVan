package com.epam.coffee_van.util;

import com.epam.coffee_van.entity.Coffee;
import com.epam.coffee_van.entity.InstantCoffee;
import com.epam.coffee_van.entity.NaturalCoffee;

public class SimpleCoffeeFactory {
	
	public Coffee createCoffee(String type) {
		Coffee coffee = null;
		if (type.equalsIgnoreCase("NaturalCoffee")) {
			coffee = new NaturalCoffee();
		} else 
			if (type.equalsIgnoreCase("InstantCoffee")) {
			coffee = new InstantCoffee();
		} else System.out.println("Coffee out of types");
		return coffee;

	}
}
