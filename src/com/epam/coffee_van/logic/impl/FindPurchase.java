package com.epam.coffee_van.logic.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.coffee_van.entity.Coffee;
import com.epam.coffee_van.entity.InstantCoffee;
import com.epam.coffee_van.entity.NaturalCoffee;
import com.epam.coffee_van.entity.Van;
import com.epam.coffee_van.logic.CoffeeVanSeacher;

public class FindPurchase implements CoffeeVanSeacher {

	private final static Logger Log = LogManager.getLogger();
	private TreeMap<Coffee, Integer> purchaseList;
	private TreeMap<Coffee, Integer> foundList;
	private Coffee coffee;

	public FindPurchase() {
		foundList = new TreeMap<Coffee, Integer>();
		purchaseList = new TreeMap<Coffee, Integer>();
		coffee = null;
	}

	@Override
	public TreeMap<Coffee, Integer> findPurchase(Van van, String type, String value) {
		if (van != null)
			purchaseList = van.getPurchaseList();
		else
			return null;
		if (purchaseList.isEmpty() && purchaseList == null)
			return null;
		if (type == null)
			return null;
		if (value == null)
			return null;
		doSearch(type, value);
		return foundList;
	}

	private void doSearch(String type, String value) {
		switch (type.toUpperCase()) {
		case "BREND":
			seachCoffeeByOther(value);
			break;
		case "TYPEBEANS":
			seachCoffeeByOther(value);
			break;
		case "WRAPPER":
			seachCoffeeByOther(value);
			break;
		case "WEIGHT":
			seachCoffeeByNumeric(Integer.parseInt(value));
			break;
		case "PRICE":
			seachCoffeeByNumeric(new BigDecimal(value));
			break;
		case "ROASTINGDEGREE":
			seachCoffeeByOther(value);
			break;
		case "GRIDINGDEGREE":
			seachCoffeeByOther(value);
			break;
		case "PRODUCTION":
			seachCoffeeByOther(value);
			break;
		case "AMOUNT":
			seachByCoffeeAmount(Integer.parseInt(value));
			break;
		}
	}

	private void seachByCoffeeAmount(Integer amount) {
		if (purchaseList.containsValue(amount)) {
			Iterator<Map.Entry<Coffee, Integer>> entries = purchaseList.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<Coffee, Integer> entry = entries.next();
				if (amount.equals(entry.getValue())) {
					foundList.put(entry.getKey(), amount);
					Log.info("Add found Coffee by Amount");
				}
			}
		}
	}

	private void seachCoffeeByNumeric(BigDecimal price) {
		Set<Coffee> coffeeSet = purchaseList.keySet();
		Iterator<Coffee> iter = coffeeSet.iterator();
		while (iter.hasNext()) {
			coffee = iter.next();
			Integer amount = purchaseList.get(coffee);
			if (price.equals(coffee.getPrice())) {
				foundList.put(coffee, amount);
				Log.info("Add found Coffee by Price");
			}
		}
	}

	private void seachCoffeeByNumeric(Integer weight) {
		Set<Coffee> coffeeSet = purchaseList.keySet();
		Iterator<Coffee> iter = coffeeSet.iterator();
		while (iter.hasNext()) {
			coffee = iter.next();
			Integer amount = purchaseList.get(coffee);
			if (weight.equals(coffee.getWeight())) {
				foundList.put(coffee, amount);
				Log.info("Add found Coffee by Weight");
			}
		}
	}

	private void seachCoffeeByOther(String value) {
		Set<Coffee> coffeeSet = purchaseList.keySet();
		Iterator<Coffee> iter = coffeeSet.iterator();
		while (iter.hasNext()) {
			coffee = iter.next();
			Integer amount = purchaseList.get(coffee);
			if (value.equals(coffee.getBrend()) || value.equals(coffee.getTypeBeans())
					|| value.equals(coffee.getWrapper())) {
				foundList.put(coffee, amount);
				Log.info("Add found NaturalCoffee");
			} else if (coffee.getClass() == NaturalCoffee.class) {
				if (value.equals(((NaturalCoffee) coffee).getRoastingDegree())
						|| value.equals(((NaturalCoffee) coffee).getGridingDegree())) {
					foundList.put(coffee, amount);
					Log.info("Add found NaturalCoffee");
				} else if (coffee.getClass() == InstantCoffee.class) {
					if (value.equals(((InstantCoffee) coffee).getProduction())) {
						foundList.put(coffee, amount);
						Log.info("Add found InstantCoffee");
					}
				}
			}
		}
	}
}
