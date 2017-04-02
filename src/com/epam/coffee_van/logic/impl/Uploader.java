package com.epam.coffee_van.logic.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.coffee_van.entity.Coffee;
import com.epam.coffee_van.entity.Van;

public class Uploader {
	private final static Logger Log = LogManager.getLogger();

	private Van van;
	private TreeMap<Coffee, Integer> stockList;
	private BigDecimal budget;
	private BigDecimal expenses;

	public Uploader(TreeMap<Coffee, Integer> stockList, int capacity, BigDecimal budget) {
		van = new Van(capacity);
		this.stockList = stockList;
		this.budget = budget;
		expenses = new BigDecimal("0");
	}

	public void uploadVan() {
		Coffee coffee;
		Integer amount;
		BigDecimal cost;
		if (stockList != null && van != null) {
			
			Iterator<Map.Entry<Coffee, Integer>> entries = stockList.entrySet().iterator();
			while (entries.hasNext() && !stockList.isEmpty() && checkBudget(expenses)) {
				Map.Entry<Coffee, Integer> entry = entries.next();
				coffee = entry.getKey();
				amount = entry.getValue();
				cost = expenses.add(coffee.getPrice().multiply(new BigDecimal(amount))); ///!!!!
				if (checkBudget(cost)) {
					van.addPurchase(coffee, amount);
					expenses = cost;  //!!!
					entries.remove();//!!
					Log.info("Uploader add Coffee to Van");
				}
			}
			
		}
	}

	private boolean checkBudget(BigDecimal cost) {
		return budget.compareTo(cost) == 1;
	}
/*
	private Coffee findMinWeight(TreeMap<Coffee, Integer> purchaseList) {

		return null;
	}

	private Coffee findMinPrice(TreeMap<Coffee, Integer> purchaseList) {

		return null;
	}*/

	@Override
	public String toString() {
		return "Uploader [van=" + van + ", stockList=" + stockList + ", budget=" + budget + ", expenses=" + expenses
				+ "]";
	}
}
