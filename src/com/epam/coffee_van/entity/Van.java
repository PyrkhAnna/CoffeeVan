package com.epam.coffee_van.entity;

import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Van {
	
	private TreeMap<Coffee, Integer> purchaseList;
	private int capacity;
	private int weight;
	private final static Logger Log = LogManager.getLogger();

	public Van() {
		purchaseList = new TreeMap<Coffee, Integer>();
		capacity = 0;
		weight = 0;
		Log.info("New Van is created");
	}

	public Van(int capacity) {
		purchaseList = new TreeMap<Coffee, Integer>();
		this.capacity = capacity;
		weight = 0;
		Log.info("New Van is created");
	}

	public Van(TreeMap<Coffee, Integer> purchaseList, int capacity) {
		this.purchaseList = new TreeMap<Coffee, Integer>(purchaseList);
		this.capacity = capacity;
		weight = 0;
		Log.info("New Van is created");
	}

	public void addPurchase(Coffee coffee, Integer amount) {
		if (checkCoffee(coffee)) {
			if (checkAmount(amount)) {
				if (checkWeight(coffee.getWeight() * amount)) {
					loadToVan(coffee, amount);
				} else {Log.info("Failure");}
			} 
		} else {
			if (checkAmount(amount)) {
				if (checkWeight(coffee.getWeight() * amount)) {
					amount = +purchaseList.get(coffee);
					loadToVan(coffee, amount);
				} else {Log.info("Failure");}
			}
		}
	}

	private void loadToVan(Coffee coffee, Integer amount) {
		purchaseList.put(coffee, amount);
		this.weight = this.weight + coffee.getWeight()*amount;
		Log.info("Load Coffee to Van");
	}
	private boolean checkWeight(int weight) {
		int check = this.weight + weight;
		return check <= this.capacity;
	}

	private boolean checkAmount(Integer amount) {
		return amount != null && amount != 0 && amount > 0;
	}

	private boolean checkCoffee(Coffee coffee) {
		return coffee != null && !purchaseList.containsKey(coffee);
	}

	public TreeMap<Coffee, Integer> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(TreeMap<Coffee, Integer> purchaseList) {
		this.purchaseList = new TreeMap<Coffee, Integer>(purchaseList);
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	@Override
	public String toString() {
		return "Van [purchaseList=" + purchaseList + ", capacity=" + capacity + ", weight=" + weight + "]";
	}

}
