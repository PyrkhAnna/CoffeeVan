package com.epam.coffee_van.logic;

import java.util.TreeMap;
import com.epam.coffee_van.entity.Coffee;
import com.epam.coffee_van.entity.Van;


public interface CoffeeVanSeacher {
	public TreeMap<Coffee, Integer> readCoffeeVanData(Van van, String type, String value);
}
