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
	private Set<Coffee> coffeeSet = null;
	private Coffee coffee = null;
	private Integer amount = null;

	public static TreeMap<Coffee, Integer> sortPurchase(TreeMap<Coffee, Integer> purchaseList, String type,
			String start, String end) {
		Van van;
		TreeMap<Coffee, Integer> sortList;
		BigDecimal budget;
		BigDecimal expenses;
		Coffee coffee;
		Integer amount;
		BigDecimal cost;
		// определить тип по которому будем сортировать
		//
		//
		Iterator<Map.Entry<Coffee, Integer>> entries = purchaseList.entrySet().iterator();
		while (entries.hasNext() && !purchaseList.isEmpty() && purchaseList != null) {
			Map.Entry<Coffee, Integer> entry = entries.next();
			coffee = entry.getKey();
			amount = entry.getValue();
			// cost = expenses.add(coffee.getPrice().multiply(new
			// BigDecimal(amount))); ///!!!!

			entries.remove();// !!
			Log.info("Uploader add Coffee to Van");

		}

		return purchaseList;
	}

	@Override
	public TreeMap<Coffee, Integer> readCoffeeVanData(Van van, String type, String value) {
		if (van != null)
			purchaseList = van.getPurchaseList();
		else
			return null;
		if (purchaseList.isEmpty() && purchaseList == null)
			return null;
		if (type == null && type.isEmpty())
			return null;
		if (value == null && value.isEmpty())
			return null;

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

		if (!purchaseList.isEmpty() && purchaseList != null) {

			Iterator<Map.Entry<Coffee, Integer>> entries = purchaseList.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<Coffee, Integer> entry = entries.next();
				coffee = entry.getKey();
				amount = entry.getValue();
				// cost = expenses.add(coffee.getPrice().multiply(new
				// BigDecimal(amount))); ///!!!!

				entries.remove();// !!
				Log.info("Uploader add Coffee to Van");

			}

		}

		return null;
	}

	private void seachByCoffeeAmount(Integer amount) {
		if (purchaseList.containsValue(amount)) {
			Iterator<Map.Entry<Coffee, Integer>> entries = purchaseList.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<Coffee, Integer> entry = entries.next();
				if (amount.equals(entry.getValue())){
					foundList.put(entry.getKey(), amount);
				}
				// Log.info("Uploader add Coffee to Van");
			}
		}
	}

	private void seachCoffeeByNumeric(BigDecimal price) {

	}

	private void seachCoffeeByNumeric(Integer weight) {

	}

	private void seachCoffeeByOther(String value) {
		coffeeSet = purchaseList.keySet();
		Iterator<Coffee> iter = coffeeSet.iterator();
		while (iter.hasNext()) {
			
			coffee = iter.next();
			if (coffee.getClass() == obj.getClass())			
			if (amount.equals(coffee.getBrend())||amount.equals(coffee.getTypeBeans())||amount.equals(coffee.getWrapper())||
					amount.equals(coffee.)||amount.equals(coffee.getBrend())||){
				//foundList.put(entry.getKey(), amount);
			}
			// Log.info("Uploader add Coffee to Van");
		}
	}

}
