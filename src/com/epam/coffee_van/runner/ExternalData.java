package com.epam.coffee_van.runner;

import java.math.BigDecimal;
import java.util.TreeMap;

import com.epam.coffee_van.entity.Coffee;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((budget == null) ? 0 : budget.hashCode());
		result = prime * result + capacity;
		result = prime * result + ((purchaseList == null) ? 0 : purchaseList.hashCode());
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
		ExternalData other = (ExternalData) obj;
		if (budget == null) {
			if (other.budget != null)
				return false;
		} else if (!budget.equals(other.budget))
			return false;
		if (capacity != other.capacity)
			return false;
		if (purchaseList == null) {
			if (other.purchaseList != null)
				return false;
		} else if (!purchaseList.equals(other.purchaseList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExternalData [purchaseList=" + purchaseList + ", budget=" + budget + ", capacity=" + capacity + "]";
	}
}
