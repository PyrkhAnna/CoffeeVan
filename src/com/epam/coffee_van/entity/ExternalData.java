package com.epam.coffee_van.entity;

import java.math.BigDecimal;
import java.util.TreeMap;

public class ExternalData {
	private TreeMap<Coffee, Integer> purchaseList;
	private BigDecimal budget;
	private int capacity;
	
	public ExternalData() {
		purchaseList = new TreeMap<Coffee, Integer>();
		budget = new BigDecimal("0");
		capacity = 0;
	}

	public ExternalData(TreeMap<Coffee, Integer> purchaseList, BigDecimal budget, int capacity) {
		super();
		this.purchaseList= new TreeMap<Coffee, Integer>(purchaseList);
		this.budget = budget;
		this.capacity = capacity;
	}
	
	public TreeMap<Coffee, Integer> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(TreeMap<Coffee, Integer> purchaseList) {
		this.purchaseList = new TreeMap<Coffee, Integer>(purchaseList);
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "ExternalData [purchaseList=" + purchaseList + ", budget=" + budget + ", capacity=" + capacity + "]";
	}
}
