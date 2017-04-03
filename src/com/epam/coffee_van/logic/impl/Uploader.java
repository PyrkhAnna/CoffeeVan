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
	private final static Logger Log = LogManager.getLogger("Uploader.class");
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
				cost = expenses.add(coffee.getPrice().multiply(new BigDecimal(amount))); 
				if (checkBudget(cost)) {
					van.addPurchase(coffee, amount);
					expenses = cost;  
					entries.remove();
					Log.info("Uploader add Coffee to Van");
				}
			}
		}
	}
	
	public Van getVan() {
		return van;
	}

	public void setVan(Van van) {
		this.van = van;
	}

	public TreeMap<Coffee, Integer> getStockList() {
		return stockList;
	}

	public void setStockList(TreeMap<Coffee, Integer> stockList) {
		this.stockList = stockList;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public BigDecimal getExpenses() {
		return expenses;
	}

	public void setExpenses(BigDecimal expenses) {
		this.expenses = expenses;
	}

	private boolean checkBudget(BigDecimal cost) {
		return budget.compareTo(cost) == 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((budget == null) ? 0 : budget.hashCode());
		result = prime * result + ((expenses == null) ? 0 : expenses.hashCode());
		result = prime * result + ((stockList == null) ? 0 : stockList.hashCode());
		result = prime * result + ((van == null) ? 0 : van.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Uploader other = (Uploader) obj;
		if (budget == null) {
			if (other.budget != null)
				return false;
		} else if (!budget.equals(other.budget))
			return false;
		if (expenses == null) {
			if (other.expenses != null)
				return false;
		} else if (!expenses.equals(other.expenses))
			return false;
		if (stockList == null) {
			if (other.stockList != null)
				return false;
		} else if (!stockList.equals(other.stockList))
			return false;
		if (van == null) {
			if (other.van != null)
				return false;
		} else if (!van.equals(other.van))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Uploader [van=" + van + ", stockList=" + stockList + ", budget=" + budget + ", expenses=" + expenses
				+ "]";
	}
}
